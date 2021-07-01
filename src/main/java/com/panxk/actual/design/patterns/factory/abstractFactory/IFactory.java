package com.panxk.actual.design.patterns.factory.abstractFactory;

/**
 * @author Mr.pxk
 * @create 2021-06-29
 **/
public interface IFactory {

    //创建口罩
    IMask createMask();
    //创建防护服
    IProtectiveSuit createSuit();
}
