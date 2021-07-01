package com.panxk.actual.design.patterns.factory.abstractFactory;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
public class LowEndProtectiveSuit implements IProtectiveSuit {
    @Override
    public void showSuit() {
        System.out.println("我是低端防护服");
    }
}