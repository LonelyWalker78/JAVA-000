package io.github.lonelyWalker78.gateway.inbound;

import io.github.lonelyWalker78.gateway.filter.HttpRequestFilter;
import io.github.lonelyWalker78.gateway.filter.TokenFilter;
import io.github.lonelyWalker78.gateway.outbound.netty4.NettyHttpClient;
import io.github.lonelyWalker78.gateway.router.HttpEndpointRouter;
import io.github.lonelyWalker78.gateway.router.OrderRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private NettyHttpClient nettyHttpClient;
    private List<HttpRequestFilter> filters = new ArrayList<>();
    private HttpEndpointRouter httpEndpointRouter;
    private List<String> endpoints = new ArrayList<String>(){{
        add("127.0.0.1:8088");
        add("127.0.0.1:8089");
    }};
    
    public HttpInboundHandler() {
        nettyHttpClient = new NettyHttpClient();
        filters.add(new TokenFilter());
        httpEndpointRouter = new OrderRouter();
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            //实现过滤器
            for (HttpRequestFilter httpRequestFilter : filters) {
                httpRequestFilter.filter(fullRequest, ctx);
            }
            nettyHttpClient.handle(fullRequest, ctx, httpEndpointRouter.route(endpoints));
    
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
