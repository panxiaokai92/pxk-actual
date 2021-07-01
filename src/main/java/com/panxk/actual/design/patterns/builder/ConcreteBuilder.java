package com.panxk.actual.design.patterns.builder;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-30
 **/
public class ConcreteBuilder extends Builder {

    private Product product = new Product();

    @Override
    public void setPart(String name, String type) {
        product.setName(name);
        product.setType(type);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
