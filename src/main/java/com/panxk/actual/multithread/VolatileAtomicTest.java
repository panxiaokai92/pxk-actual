package com.panxk.actual.multithread;

/**
 * @description:  Volatile 不能保证原子性操作
 * @author: Mr.pxk
 * @create: 2020-05-03
 **/
public class VolatileAtomicTest {

    public static volatile int num = 0;

    //添加synchronized关键字，保证原子性
    public static synchronized void increase(){
        num ++;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        //执行循环操作
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000 ; j++) {
                        increase();     //num = num + 1
                    }
                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(num);
    }
}
