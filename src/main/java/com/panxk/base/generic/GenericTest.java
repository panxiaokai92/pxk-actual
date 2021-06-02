package com.panxk.base.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-05-31
 **/
public class GenericTest<T> {

    public void print0(T t){
        System.out.println(t);
    }

    public static  <T> void print1(T t){
        System.out.println(t);
    }

    public static <K> void print2(K k){
        System.out.println(k);
    }
}

class Demo {
    public static void main(String[] args) {

        GenericTest<String> gerTest = new GenericTest<>();
//        gerTest.print0(new Integer(1));
        gerTest.print1(new Integer(1));
        gerTest.print2(new Integer(2));

        List<String> arrList = new ArrayList<>();

    }
}
