package com.panxk.actual.multithread;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-02
 **/
public class MyThread extends Thread {

    public static int x = 500;
    private String name;
    //多线程共享
    public static int i = 0, j = 0, k = 0;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public synchronized void run() {

        while (true) {
            //控制锁力度问题
            synchronized (MyThread.class) {
                if (x > 0) {
                    System.out.println(name + "售出第" + x + "张票");

                    if (name.equals("窗口1")) {
                        i++;
                    }
                    if (name.equals("窗口2")) {
                        j++;
                    }
                    if (name.equals("窗口3")) {
                        k++;
                    }
                    x--;
                    System.out.println(Thread.currentThread().getName() + "窗口1售出" + i + ",窗口2售出" + j + ",窗口3售出" + k);
                } else {
                    //结束循环
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread("窗口1");
        MyThread myThrea2 = new MyThread("窗口2");
        MyThread myThrea3 = new MyThread("窗口3");

        myThread.start();
        myThrea2.start();
        myThrea3.start();
    }
}


