package com.panxk.actual.design.patterns.strategy;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
// 减法算法
public class SubtractStrategy implements Strategy{
    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}