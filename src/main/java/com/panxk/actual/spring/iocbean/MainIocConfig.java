package com.panxk.actual.spring.iocbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-13
 **/
@Configuration
@ComponentScan(basePackages = {"com.panxk.actual.spring.iocbean"} )
public class MainIocConfig {

    @Bean(initMethod = "initPersonIoc")
    public PersonIoc personIoc(){
        PersonIoc personIoc = new PersonIoc();
        personIoc.setName("panxiaokai");
        personIoc.setSex("男");
        return personIoc;
    }


}
