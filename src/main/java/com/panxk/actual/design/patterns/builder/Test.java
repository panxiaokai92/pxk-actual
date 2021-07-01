package com.panxk.actual.design.patterns.builder;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-30
 **/
public class Test {

    public static void main(String[] args) {
        // 店长
        Director director = new Director();
        // 得到宝马汽车，内部实现提取宝马汽车的详情操作
        Product product = director.getBProduct();
        // 展示汽车信息
        product.showProduct();

    }
}
