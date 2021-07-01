package com.panxk.actual.design.patterns.strategy;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    // 动态替换算法(策略)
    public void replaceStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int a, int b) {
        return strategy.calculate(a, b);
    }
}