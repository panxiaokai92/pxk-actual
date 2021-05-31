package com.panxk.actual.springboot.setInject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        InstA instA = (InstA) context.getBean("instA");
        System.out.println("instA: "+instA);

//        Person person = (Person) context.getBean("person");
//        System.out.println("person: " + person);
    }
}
