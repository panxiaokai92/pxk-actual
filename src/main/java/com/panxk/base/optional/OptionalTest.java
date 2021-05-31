package com.panxk.base.optional;

import java.util.Optional;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2021-05-31
 **/
public class OptionalTest {

    public static void main(String[] args) throws Exception {

        OptionalBean optBean = new OptionalBean();

        //1.orElse 和 orElseGet区别
        String str = Optional.ofNullable(optBean.getName()).orElseGet(() -> {
            String ageName = "";
            if ("1".equals(optBean.getAge())) {
                ageName = "hello age 1";
            } else {
                ageName = "hello age 2";
            }
            return ageName;
        });
        System.out.println(str);

        //2.map使用, 同时判断optBean和optBean.getName为空
        String name = Optional.ofNullable(optBean).map(opt -> opt.getName()).orElse("Unknow Name");
        System.out.println(name);

        //3.isPresent()
        if (Optional.ofNullable(optBean).map(opt -> opt.getName()).isPresent()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        //4.ifPresent()
        Optional.ofNullable(optBean).ifPresent(opt -> System.out.println("姓名："+ opt.getName()));

        //5.filter
        optBean.setName("hello");
        Optional<OptionalBean> helloOpt = Optional.ofNullable(optBean).filter(opt -> opt.getName().equals("hello"));
        System.out.println(helloOpt.isPresent());

        //6.Optional.orElseThrow()
        Optional.ofNullable(optBean).orElseThrow(() -> new Exception("未查询到该对象！"));

        //7.orElseGet
        // optBean1不为空时，输出结果：Creating New User, optBean1 create,optBean2 create
        // 两个Optional对象都包含非空值，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
        //在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
        OptionalBean optBean1 = new OptionalBean("name1",1);
        Optional.ofNullable(optBean1).orElse(createNewUser());
        System.out.println("optBean1 create");

        Optional.ofNullable(optBean1).orElseGet(() -> createNewUser());
        System.out.println("optBean2 create");

        //8.faltMap

        //问题：使用Optional的可读性有些时候太差，需要考量
        //map , ofNullable 可以无限级连用

    }

    private static OptionalBean createNewUser() {
        System.out.println("Creating New User");
        return new OptionalBean("extra@gmail.com", 12);
    }
}
