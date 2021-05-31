package com.panxk.actual.springboot.setInject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Component
public class PxkBeanFacoruPostProcessor implements BeanFactoryPostProcessor {
    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     *
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        //bean定义
        GenericBeanDefinition rootBeanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("instA");

        //1、修改instA的bean定义，class属性
        //rootBeanDefinition.setBeanClass(InstD.class);

        //2、autowireMode属性
        //2.1 AUTOWIRE_NO  显示的在我们的字段上面写一个@Autowired
        //2.2 AUTOWIRE_BY_NAME 属性注入 byName
        //rootBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);

        //3、AUTOWIRE_CONSTRUCTOR 构造器注入模式
        GenericBeanDefinition personBeanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("person");

        //自动识别属性字段，查找对应构造器
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0, "pxk");

        personBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);

    }
}
