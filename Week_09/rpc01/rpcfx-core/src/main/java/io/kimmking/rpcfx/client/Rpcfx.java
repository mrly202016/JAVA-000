package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Rpcfx {

    private static Map<Class<?>,Object> cache=new ConcurrentHashMap<>();
    private static Bootstrap b;
    private static volatile String ans="";

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
        EventLoopGroup group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new HttpClientCodec());
                        p.addLast(new HttpContentDecompressor());
                        p.addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) {
                                ctx.flush();
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                if (msg instanceof HttpContent) {
                                    HttpContent content = (HttpContent) msg;

                                    ans=content.content().toString(CharsetUtil.UTF_8);

                                    if (content instanceof LastHttpContent) {
                                        ctx.close();
                                    }
                                }
                            }
                        });
                    }
                });
    }

    public static <T> T create(final Class<T> serviceClass, final String url) throws IllegalAccessException, InstantiationException {

        // 0. 替换动态代理 -> AOP
//        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url));
        Class<?> proxy = new ByteBuddy()
                .subclass(Object.class)
                .implement(serviceClass)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new RpcfxInvocationHandler(serviceClass,url)))
                .make()
                .load(Rpcfx.class.getClassLoader())
                .getLoaded();
        cache.putIfAbsent(serviceClass,proxy.newInstance());
        return (T) cache.get(serviceClass);

    }

    public static class RpcfxInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            RpcfxResponse response = post(request, url);

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException, InterruptedException, URISyntaxException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            /*OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: "+respJson);*/

            String host=url.substring(url.indexOf("/")+2,url.lastIndexOf(":"));
            int port=Integer.parseInt(url.substring(url.lastIndexOf(":")+1,url.length()-1));
            Channel ch = b.connect(host, port).sync().channel();

            FullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.POST, new URI(url).getRawPath());

            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().add(HttpHeaderNames.CONTENT_TYPE, "application/json");
            ByteBuf bbuf = Unpooled.copiedBuffer(reqJson, StandardCharsets.UTF_8);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, bbuf.readableBytes());
            request.content().clear().writeBytes(bbuf);

            // Send the HTTP request.
            ch.writeAndFlush(request);
            while(ans==null||ans.length()==0);
            return JSON.parseObject(ans, RpcfxResponse.class);
        }
    }
}
