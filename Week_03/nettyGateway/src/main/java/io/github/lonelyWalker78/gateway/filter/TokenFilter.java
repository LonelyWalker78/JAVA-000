package io.github.lonelyWalker78.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Description: 网关过滤器 header增加token参数
 * Created on 2020/11/4
 *
 * @author lincj
 */
public class TokenFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("token", "hello, I im lincj");
    }
}
