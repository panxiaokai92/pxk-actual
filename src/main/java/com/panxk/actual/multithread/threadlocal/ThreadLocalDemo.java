package com.panxk.actual.multithread.threadlocal;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-08
 **/
public class ThreadLocalDemo {

    //线程数
    private final static int THREADS_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

        InnerClass innerClass = new InnerClass();
        for (int i = 1; i <= THREADS_COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 4; j++) {
                        innerClass.add(String.valueOf(j));
                        innerClass.print();
                    }
                    innerClass.set("hello world");

                    /*
                     * 通知CountDownLatch对象，他们已经完成了各自的任务
                     * 每当一个线程完成了自己的任务后，计数器的值就会减1
                     * 所以当N个线程都调用了这个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。
                     */
                    countDownLatch.countDown();
                }
            }, "Thread-" + i).start();
        }
        /**
         * 主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
         * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
         * 每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁（Latch）上等待的线程就可以恢复执行任务。
         */
        countDownLatch.await();

        System.out.println("所有线程执行完毕");
        System.out.println("主线程继续执行。。。");

    }


    private static class InnerClass {

        void add(String newStr) {
            StringBuilder str = Counter.counter.get();
            Counter.counter.set(str.append(newStr));
        }

        void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {
        /*
         * get时如果线程本地变量为null，则默认初始化一个这个变量类型的实例。
         * StringBuilder为非线程安全的类型，通过ThreadLocal本地化则可以实现线程安全
         */
        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };
    }


}


