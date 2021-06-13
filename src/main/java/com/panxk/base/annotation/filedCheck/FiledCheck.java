package com.panxk.base.annotation.filedCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FiledCheck {

    int min() default 0;
    int max() default 0;
    String error() default "";
}
