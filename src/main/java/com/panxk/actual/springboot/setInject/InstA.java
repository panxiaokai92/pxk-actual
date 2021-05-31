package com.panxk.actual.springboot.setInject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Component
public class InstA {

    @Autowired
    private InstB instB;

    public InstB getInstB(){
        return instB;
    }

    public void setInstB(InstB instB) {
        this.instB = instB;
    }

    public InstA() {
        System.out.println("InstA 的构造方法。。。。。");
    }

    @Override
    public String toString() {
        return "InstA{" +
                "instB=" + instB +
                '}';
    }
}
