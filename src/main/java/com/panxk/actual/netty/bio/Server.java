package com.panxk.actual.netty.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-09
 **/
public class Server {

    // 此处不能throws IOException，必须try{}catch{}
    // client -> server 连接时，如果server异常时，直接throws，连接不会关闭
    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress("127.0.0.1",8888));

            while (true) {
                //阻塞方法，client连接到server时，唤醒
                Socket socket = ss.accept();

                new Thread(() -> {
                    handle(socket);
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理数据
     * @param socket
     */
    static void handle(Socket socket) {
        try {
            //Socket是单向的，必须自己获取输入、输出流
            byte[] bytes = new byte[1024];
            int len = socket.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0 , len));

            socket.getOutputStream().write(bytes, 0, len);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
