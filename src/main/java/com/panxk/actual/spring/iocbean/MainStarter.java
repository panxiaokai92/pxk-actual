package com.panxk.actual.spring.iocbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-13
 **/
public class MainStarter {

    public static void main(String[] args){
        //xml配置创建容器
        //ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("bean.xml");

        //JavaConfig创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainIocConfig.class);

        //AnnotationConfigWebApplicationContext context2 = new AnnotationConfigWebApplicationContext();
        //context2.register();

        PersonIoc personIoc = (PersonIoc) context.getBean("personIoc");
        System.out.println(personIoc);

    }
}
