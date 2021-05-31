package com.panxk.actual.jvm;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-19
 **/
public class Math {

    public static final int initData = 666;
    public static User user = new User();

    public static void main(String[] args) {
        Math mathTest = new Math();
        mathTest.compute();

        System.out.println("jvm study test .....");
    }

    //一个方法对应一块栈帧内存区域
    public int compute(){
        int a =1;
        int b =2;
        int c = (a+b)*10;
        return c;
    }
}
