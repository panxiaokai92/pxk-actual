package com.panxk.actual.netty.aio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-11
 **/
public class AioPoolServer {

    public static void main(String[] args) throws InterruptedException, IOException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //如果设置多个线程池时，修改initialSize参数
        AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

        //将组threadGroup传进来，其他逻辑跟单线程版本一样
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(threadGroup)
                .bind(new InetSocketAddress(8888));
        //不阻塞，主线程执行完就结束了，当有连接过来时，操作系统执行回调方法
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            /**
             * Invoked when an operation has completed.
             * @param client     The result of the I/O operation.
             * @param attachment
             */
            @SneakyThrows
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                serverChannel.accept(null, this);

                System.out.println(client.getRemoteAddress());
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //执行回调方法
                client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        attachment.flip();
                        System.out.println(new String(attachment.array(), 0 , result));
                        client.write(ByteBuffer.wrap("hello client".getBytes()));
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                    }
                });
            }

            /**
             * Invoked when an operation fails.
             * @param exc        The exception to indicate why the I/O operation failed
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

        while (true) {
            Thread.sleep(1000);
        }

    }
}
