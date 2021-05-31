package com.panxk.actual.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-05-03
 **/
public class UnSafeInstance {

    public static Unsafe reflectGetUnsafe(){

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    };
}
