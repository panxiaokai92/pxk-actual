package com.panxk.actual.multithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-05
 **/
public class SemaphoreTest {

    static int count;

    //初始化信号量
    static final Semaphore s = new Semaphore(1);

    /**
     * 用信号量保证互斥
     * @throws InterruptedException
     */
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count += 1;
        } finally {
            s.release();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


    }


}
