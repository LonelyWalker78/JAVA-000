package io.github.lonelyWalker78.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {

    private ByteBuf buf = Unpooled.buffer();
    private FullHttpRequest fullRequest;
    private ChannelHandlerContext ctx11;

    public NettyHttpClientOutboundHandler(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx11) {
        this.fullRequest = fullRequest;
        this.ctx11 = ctx11;
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        ctx.writeAndFlush(fullRequest);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpResponse) {
            DefaultHttpResponse response = (DefaultHttpResponse)msg;
        }
        if (msg instanceof HttpContent) {
            DefaultLastHttpContent chunk = (DefaultLastHttpContent)msg;
            buf.writeBytes(chunk.content());
            if (chunk instanceof LastHttpContent) {
                String content = buf.toString(Charset.forName("UTF-8"));
                buf.clear();
                handleResponse(fullRequest, ctx11, content);
            }
        }
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, String content) {
        FullHttpResponse response = null;
        try {
            byte[] body = content.getBytes("UTF-8");
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", content.length());
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            NettyHttpClient.exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }
}