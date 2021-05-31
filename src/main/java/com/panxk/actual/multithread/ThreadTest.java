package com.panxk.actual.multithread;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-04
 **/
public class ThreadTest {


    public static void main(String[] args) throws InterruptedException {

        Thread ta = new Thread(() -> {
            System.out.println("Thread A ===== ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 1000; i++) {
                System.out.println("current i:" + i);
            }
        });
        //ta.run();
        ta.start();
        //ta.join();

        Thread tb = new Thread(() -> {
            System.out.println("Thread B start ===== ");
            try {
                ta.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B end ===== ");
        });
        //tb.run();
        tb.start();

        LockSupport.park(ta);

        //线程池
//        ExecutorService expool = Executors.newFixedThreadPool(10);
//        expool.execute(()->{
//            System.out.println("hello ");
//        });

    }
}
