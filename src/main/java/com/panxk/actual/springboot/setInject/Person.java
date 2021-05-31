package com.panxk.actual.springboot.setInject;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Component
@Data
public class Person {

    private Integer age;
    private String name;

    public Person(String name) {
        this.name = name;
        System.out.println("person有参构造器 ---userName");
    }

    public Person() {
        System.out.println("person无参构造器");
    }

    public Person(Integer age) {
        this.age = age;
        System.out.println("person有参构造器 ---age");
    }
}
