package com.panxk.actual.design.patterns.factory.abstractFactory;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
public class LowEndFactory implements IFactory {
    @Override
    public IMask createMask() {
        IMask mask =  new LowEndMask();
        // .....
        //  LowEndMask的100行初始化代码
        return mask;
    }

    @Override
    public IProtectiveSuit createSuit() {
        IProtectiveSuit suit =  new LowEndProtectiveSuit();
        // .....
        //  LowEndProtectiveSuit的100行初始化代码
        return suit;
    }
}