package homework.inbound;

import homework.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/4
 * Time: 20:12
 */
public class HttpInboundFilter extends ChannelInboundHandlerAdapter implements HttpRequestFilter {

    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add("nio","heheda");
        ctx.fireChannelRead(fullRequest);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("channelRead流量接口请求开始，时间为{}"+System.currentTimeMillis());
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        filter(fullRequest,ctx);
    }
}
