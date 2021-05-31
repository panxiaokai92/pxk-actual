package com.panxk.actual.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-11
 **/
public class HelloNetty {

    public static void main(String[] args) {
        new NettyServer(8888).serverStart();
    }

}

class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void serverStart() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //第一个group是selector,后面的是工作线程组
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)    //指定通道类型
                //监听事件处理
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //通道建立，在通道的末尾添加处理器
                        socketChannel.pipeline().addLast(new Handler());
                    }
                });

        try {
            ChannelFuture cf = serverBootstrap.bind(port).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}

/**
 * 监听处理器，添加处理逻辑
 */
class Handler extends ChannelInboundHandlerAdapter {
    /**
     * 封装的很好，读取数据部分已经实现，直接就能用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("server: channel read");
        ByteBuf buf = (ByteBuf) msg;

        //给客户端回写
        System.out.println(buf.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);
        ctx.close();
    }

    /**
     * 异常的处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
