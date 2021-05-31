package com.panxk.actual.springboot.setInject;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
public class TulingImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{"com.panxk.actual.springboot.setInject.InstF"};
    }
}
