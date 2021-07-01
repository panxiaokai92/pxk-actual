package com.panxk.actual.design.patterns.strategy;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-29
 **/
public class Test {

    public static void main(String[] args) {
        Strategy addStrategy = new AddStrategy();
        Context context = new Context(addStrategy);
        // 输出3
        System.out.println(context.calculate(1, 2));

        Strategy subStrategy = new SubtractStrategy();
        // 动态替换算法(策略)
        context.replaceStrategy(subStrategy);
        // 输出-1
        System.out.println(context.calculate(1, 2));
    }
}
