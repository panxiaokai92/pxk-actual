package com.panxk.actual.multithread;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-04
 **/
public class VolatileVisibilityTest {

    //添加volatile关键字，测试可见性
    private static volatile boolean inifFlag = false;

    public static void main(String[] args) throws InterruptedException {

        Thread ta = new Thread(() -> {
            System.out.println("waitting data ...");
            while (!inifFlag) {

            }
            System.out.println("visibility success ");
        });
        ta.start();

        Thread.sleep(2000);

        Thread tb = new Thread(() -> {
            System.out.println("change status success start");
            inifFlag = true;
            System.out.println("change status success end");
        });

        //ta.start();
        tb.start();
    }
}
