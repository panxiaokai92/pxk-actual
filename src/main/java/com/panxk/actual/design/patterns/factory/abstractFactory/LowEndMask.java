package com.panxk.actual.design.patterns.factory.abstractFactory;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
public class LowEndMask implements IMask {
    @Override
    public void showMask(){
        System.out.println("我的低端口罩");
    }
}