package com.panxk.actual.spring.iocbean;

import org.springframework.beans.factory.InitializingBean;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-13
 **/
public class PersonIoc implements InitializingBean {

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public PersonIoc() {
        System.out.println("构造方法 。。。。。");
    }

    public void initPersonIoc(){
        System.out.println("initPersonIoc .....方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ..... 方法");

    }
}
