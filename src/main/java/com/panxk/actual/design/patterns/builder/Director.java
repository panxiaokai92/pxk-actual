package com.panxk.actual.design.patterns.builder;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-30
 **/
public class Director {

    private Builder builder = new ConcreteBuilder();

    public Product getAProduct() {
        builder.setPart("奥迪汽车", "Q5");
        return builder.getProduct();
    }

    public Product getBProduct() {
        builder.setPart("宝马汽车", "X7");
        return builder.getProduct();
    }
}