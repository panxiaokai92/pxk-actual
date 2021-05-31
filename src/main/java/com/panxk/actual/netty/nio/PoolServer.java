package com.panxk.actual.netty.nio;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: nio 线程池版本
 * @author: Mr.pxk
 * @create: 2020-05-11
 **/
public class PoolServer {

    ExecutorService pool = Executors.newFixedThreadPool(50);

    private Selector selector;


    public static void main(String[] args) throws IOException {

        PoolServer poolServer = new PoolServer();
        poolServer.intiServer(8000);
        poolServer.listen();
    }


    /**
     * 初始化服务
     * @param port
     * @throws IOException
     */
    private void intiServer(int port) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置非阻塞
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(port));

        this.selector = Selector.open();
        //注册监听事件类型 OP_ACCEPT
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功！");
    }

    /**
     * 监听服务
     */
    private void listen() throws IOException {

        while(true) {

            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                    SocketChannel channel = ssc.accept();
                    channel.configureBlocking(false);
                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {

                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(key));
                }
            }
        }
    }
}

class ThreadHandlerChannel extends Thread {

    private SelectionKey key;

    public ThreadHandlerChannel(SelectionKey key){
        this.key = key;
    }

    @SneakyThrows
    @Override
    public void run(){
        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            int size = 0;
            while ((size = channel.read(byteBuffer)) > 0 ) {
                byteBuffer.flip();
                baos.write(byteBuffer.array(), 0 ,size);
                byteBuffer.clear();
            }
            baos.close();

            byte[] content = baos.toByteArray();
            ByteBuffer writeBuff = ByteBuffer.allocate(content.length);
            writeBuff.put(content);
            writeBuff.flip();

            channel.write(writeBuff);
            if (size == -1) {
                channel.close();
            } else {
                key.interestOps(key.interestOps() | SelectionKey.OP_READ) ;
                key.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}