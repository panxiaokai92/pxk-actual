package com.panxk.actual.spring.iocbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-18
 **/
@Component
public class PersonBeanPostprocessor implements BeanPostProcessor {

    /**
     * 前置处理器
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof PersonIoc) {
            PersonIoc personIoc = new PersonIoc();
            personIoc.setSex("人妖");
            return personIoc;
        }

        return bean;
    }

    /**
     * 后置处理器
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
