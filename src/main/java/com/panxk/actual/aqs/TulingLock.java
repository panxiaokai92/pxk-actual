package com.panxk.actual.aqs;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-03
 **/
public class TulingLock {

    /**
     * 锁状态
     */
    private static int state = 0;

    /**
     * 当前持有线程
     */
    private Thread lockHolder;

    /**
     * 未获取不到锁的队列
     */
    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    /**
     * Unsafe必须通过BootStrap加载，不然直接new会抛异常，通过反射机制实现
     */
    private static final Unsafe unsafe = UnSafeInstance.reflectGetUnsafe();
    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(TulingLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new Error();
        }
    }


    /**
     * 原子操作
     * @param except
     * @param update
     * @return
     */
    public final boolean compareAndSwapState(int except, int update){
        return unsafe.compareAndSwapInt(this, stateOffset, except, update);
    }

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        TulingLock.state = state;
    }

    public Object getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    public boolean aquire(){

        //cas比较与交换-原子算法
        Thread thread = Thread.currentThread();
        //初始状态
        int c = getState();
        //同步器还没有被持有
        if (c == 0) {
            //size() == 0 ,解决了当前线程初次进来，队列中没有等待的线程，当前线程能进行加锁的问题
            if (( waiters.size() == 0 || thread == waiters.peek()) && compareAndSwapState(c, 1)){
                lockHolder  = thread;
                return true;
            }
        }

        return false;
    }


    /**
     * 加锁
     */
    public void lock(){

        //加锁成功
        if (aquire()) {
            return;
        }

        Thread current = Thread.currentThread();
        waiters.add(current);

        //自旋等待
        for (;;) {
            //让出cpu的使用权
            //Thread.yield();
            if ((current == waiters.peek()) &&  aquire()) {
                waiters.poll();   //T2从队列中移除
                return;
            }

            //阻塞当前线程, 释放cpu的使用权
            LockSupport.park(current);
        }
    }

    /**
     * 释放锁
     */
    public void unlock(){
        if (Thread.currentThread() != lockHolder) {
            throw new RuntimeException("lockholder is not current thread");
        }

        int state = getState();
        if (compareAndSwapState(state, 0)){
            //当前持有线程释放
            setLockHolder(null);

            //唤醒队列排队的线程，尝试获取锁
            Thread first = waiters.peek();
            if (first !=  null) {
                LockSupport.unpark(first);
            }
        }

    }

}
