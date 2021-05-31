package com.panxk.actual.springboot.setInject;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
public class TulingImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata
            , BeanDefinitionRegistry beanDefinitionRegistry) {

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(InstE.class);

        beanDefinitionRegistry.registerBeanDefinition("instD", rootBeanDefinition);


    }
}
