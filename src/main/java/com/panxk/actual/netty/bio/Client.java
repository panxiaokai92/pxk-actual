package com.panxk.actual.netty.bio;


import java.io.IOException;
import java.net.Socket;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-09
 **/
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            socket.getOutputStream().write("hello panxiakai server".getBytes());
            socket.getOutputStream().flush();

            System.out.println("write over, waitting for back...");
            byte[] bytes = new byte[1024];
            int len = socket.getInputStream().read(bytes);

            System.out.println(new String(bytes, 0 , len));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
