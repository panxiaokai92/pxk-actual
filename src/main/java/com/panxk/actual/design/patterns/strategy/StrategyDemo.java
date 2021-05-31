package com.panxk.actual.design.patterns.strategy;

/**
 * @description: 策略模式
 * @author: Mr.pxk
 * @create: 2020-04-15
 **/
public class StrategyDemo {

    public static void main(String[] args) {

        //加法
        Context context = new Context(new OperateAdd());
        System.out.println("10 + 5 = "+ context.exectOperate(10, 5));

        //减法
        Context context1 = new Context(new OperateSub());
        System.out.println("10 - 5 = "+ context1.exectOperate(10, 5));

        //乘法
        Context context2 = new Context(new OperateMulity());
        System.out.println("10 * 5 = "+ context2.exectOperate(10, 5));

    }

    public static class Context {
        private Strategy strategy;

        public Context(Strategy strategy) {
            this.strategy = strategy;
        }

        //方法执行
        public int exectOperate(int num1, int num2){
            return strategy.doOperation(num1, num2);
        }

    }


    //接口
    public interface Strategy {
        int doOperation(int num1, int num2);
    }

    //加法
    public static class OperateAdd implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }

    //减法
    public static class OperateSub implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    //乘法
    public static class OperateMulity implements Strategy{
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2 ;
        }
    }
}

