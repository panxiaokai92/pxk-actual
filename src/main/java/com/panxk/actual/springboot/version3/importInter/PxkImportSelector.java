package com.panxk.actual.springboot.version3.importInter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Slf4j
public class PxkImportSelector implements ImportSelector {

    private Class<?> getSpringFacoriesLoaderFactoryClass(){
        return PxkEnableAutoConfig.class;
    }

    /**
     * 方法2 动态读取配置文件
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        List<String> configurations = getCandidateConfigurations();

        return StringUtils.toStringArray(configurations);
    }

    /**
     * 方法1 硬编码写死路径
     * @return
     */
//    @Override
//    public String[] selectImports(AnnotationMetadata annotationMetadata) {
//        return new String[]{"com.panxk.actual.springboot.version3.config.SpringRedisAutoConfig"};
//    }


    /**
     * 读取配置
     * @return
     */
    protected List<String> getCandidateConfigurations(){
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFacoriesLoaderFactoryClass()
                , PxkImportSelector.class.getClassLoader());
        Assert.notEmpty(configurations,"No auto configuration classes found in META-INF/spring.properties.");

        return configurations;
    }
}
