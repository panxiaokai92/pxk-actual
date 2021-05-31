package com.panxk.actual.springboot.version1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Slf4j
public class TestXml {

    public static void main(String[] args) {

        //获取IOC容器
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");

        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
        redisTemplate.opsForValue().set("pxk","潘小凯");

        System.out.println("从redis中获取："+ redisTemplate.opsForValue().get("pxk"));
    }

}
