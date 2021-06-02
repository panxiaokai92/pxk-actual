package com.panxk.base.generic;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-05-31
 **/
public class AnnoTest {

    @AnnoInfaceTest("hello world！")
    public static void main(String[] args) throws NoSuchMethodException {

        Class cls = AnnoTest.class;
        Method method = cls.getMethod("main", String[].class);
        AnnoInfaceTest anno = method.getAnnotation(AnnoInfaceTest.class);
        System.out.println(anno.value());

    }
}
