package com.panxk.actual.design.patterns.decorator;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-15
 **/
public class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
