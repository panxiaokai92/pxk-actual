package com.panxk.actual.design.patterns.builder;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-30
 **/
public abstract class Builder {

    public abstract void setPart(String name, String type);

    public abstract Product getProduct();
}
