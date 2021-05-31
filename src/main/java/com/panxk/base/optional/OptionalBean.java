package com.panxk.base.optional;

import lombok.Data;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-05-31
 **/
@Data
public class OptionalBean {

    private String name;
    private Integer age;

    public OptionalBean() {
    }

    public OptionalBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
