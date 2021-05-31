package com.panxk.actual.design.patterns.decorator;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-15
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
