package com.panxk.actual.springboot.version3;

import com.panxk.actual.springboot.version3.config.RedisAutoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Slf4j
public class TestAutoJavaConfig {

    public static void main(String[] args) {

        //注解版本的IOC容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RedisAutoConfig.class);
        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

        redisTemplate.opsForValue().set("pxk","潘小凯AutoConfig");

        System.out.println("redis config 取值："+ redisTemplate.opsForValue().get("pxk"));
    }
}
