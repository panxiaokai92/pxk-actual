package com.panxk.actual.multithread;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-05
 **/
public class FutureTaskTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        // 创建并启动线程
        Thread T1 = new Thread(futureTask);
        T1.start();
        // 获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);


        //线程池版本
        // 创建FutureTask
        FutureTask<Integer> futureTask2
                = new FutureTask<>(()-> 1+100);
        // 创建线程池
        ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        // 提交FutureTask
        es.submit(futureTask2);
        // 获取计算结果
        Integer result2 = futureTask2.get();






    }
}
