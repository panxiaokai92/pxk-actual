package com.panxk.actual.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-05
 **/
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10
                ,30, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(10000));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());


        while (true) {
            CountDownLatch latch = new CountDownLatch(2);

            executor.execute(() -> {
                System.out.println("t1 sout ");
                latch.countDown();
            });


            executor.execute(() ->{
                System.out.println("t2 sout");
                latch.countDown();
            });

            //等待两个线程操作结果
            executor.wait();

            //todo
            System.out.println("todo something success");

        }



    }
}
