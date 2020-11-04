package io.github.lonelyWalker78.gateway.outbound.netty4;

import io.github.lonelyWalker78.gateway.outbound.httpclient4.NamedThreadFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Description: 使用netty client调用业务服务器接口
 * Created on 2020/11/4
 *
 * @author lincj
 */
public class NettyHttpClient {

    private ExecutorService proxyService;

    public NettyHttpClient() {
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, String backendUrl) throws Exception {
        String[] arr = backendUrl.split(":");
        String host = arr[0];
        int port = Integer.valueOf(arr[1]);
        proxyService.submit(()->fetchGet(fullRequest, ctx, host, port));
    }

    public void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, String host, int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    //客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(fullRequest, ctx));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();


            f.channel().write(fullRequest);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT)).addListener(ChannelFutureListener.CLOSE);
            ctx.flush();
            exceptionCaught(ctx, e);
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}