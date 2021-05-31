package com.panxk.actual.springboot.setInject;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Component
public class InstD {

    public InstD() {
        System.out.println("instD 构造方法");
    }
}
