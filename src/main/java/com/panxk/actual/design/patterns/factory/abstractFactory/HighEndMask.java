package com.panxk.actual.design.patterns.factory.abstractFactory;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
public class HighEndMask implements IMask {
    @Override
    public void showMask() {
        System.out.println("我是高端口罩");
    }
}