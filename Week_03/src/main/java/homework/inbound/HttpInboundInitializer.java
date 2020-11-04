package homework.inbound;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/2
 * Time: 11:14
 */
public class HttpInboundInitializer extends ChannelInitializer {
    private String proxyServer;

    public HttpInboundInitializer(String proxyServer) {
        this.proxyServer=proxyServer;
    }

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
//		if (sslCtx != null) {
//			p.addLast(sslCtx.newHandler(ch.alloc()));
//		}
        p.addLast(new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpInboundFilter());
        p.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
