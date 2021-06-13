package com.panxk.base.annotation;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-06-13
 **/
public class BusinessLogin {

    @Todo(level = Todo.Level.LOW)
    public void notStart(){
    }

    @Todo(level = Todo.Level.MEDIUM, status = Todo.Status.YES, author = "panxiaokai")
    public void mediunMethod(){
    }

    @Todo(level = Todo.Level.HIGH, status = Todo.Status.YES, author = "hello")
    public void HighMethod(){
    }

    public static void main(String[] args) {

        //反射
        Class businessLoginClass = BusinessLogin.class;

        Method[] methods = businessLoginClass.getMethods();
        for (Method  method : methods) {
            //获取注解
            Todo todoAnno = method.getAnnotation(Todo.class);
            if (todoAnno != null) {
                //输出属性
                System.out.println("methodName:" + method.getName());
                System.out.println("author:" + todoAnno.author());
                System.out.println("level:" + todoAnno.level());
                System.out.println("status:" + todoAnno.status());
            }
        }
    }
}
