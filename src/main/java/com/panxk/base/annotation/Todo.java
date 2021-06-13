package com.panxk.base.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panxiaokai
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Todo {

    public enum Level{ LOW, MEDIUM, HIGH}
    public enum Status{YES, NO}

    String author() default "panxiaokai";
    Level level() default Level.LOW;
    Status status() default Status.NO;
}
