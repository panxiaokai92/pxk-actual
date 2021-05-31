package com.panxk.actual.netty.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-10
 **/
public class SingleThreadServer {

    public static void main(String[] args) throws IOException {
        //双向通道，能读能写，不需要getInputStream， getOutputStream
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1",8888));
        //设置非阻塞
        ssc.configureBlocking(false);

        System.out.println("server started, listening on : "+ ssc.getLocalAddress());
        //注册对哪一类事件进行处理， OP_ACCEPT： 有客户端连接
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            //一直轮询监听server，直到有事件发生（client连接）
            selector.select();

            //可以理解为一个key，代表一个事件发生了的server连接（未发生的不会存放到keys中）
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handle(key);
            }
        }
    }

    /**
     * 处理逻辑
     * @param key
     */
    private static void handle(SelectionKey key) {
        //接收到事件请求
        if (key.isAcceptable()) {
            try {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                //设置非阻塞
                sc.configureBlocking(false);

                //建立连接channel之后，再注册一个read事件，准备接收数据
                sc.register(key.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        } else if (key.isReadable()) {
            //read数据逻辑
            SocketChannel sc = null;

            try {
                //连接通道
                sc = (SocketChannel) key.channel();

                ByteBuffer readBuff = ByteBuffer.allocate(512);
                readBuff.clear();

                int readLen = sc.read(readBuff);
                if (readLen != -1) {
                    System.out.println(new String(readBuff.array(), 0 ,readLen));
                }

                ByteBuffer writeBuff = ByteBuffer.wrap("HelloClient".getBytes());
                sc.write(writeBuff);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭连接
                if (sc != null) {
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
