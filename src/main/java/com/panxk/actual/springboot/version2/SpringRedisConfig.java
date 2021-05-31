package com.panxk.actual.springboot.version2;

import org.springframework.context.annotation.Configuration;

/**
 * @description: JavaConfig版本的装配， 等同于spring-redis.xml
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Configuration
public class SpringRedisConfig {

//    @Bean
//    public JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(30);
//        jedisPoolConfig.setMaxIdle(18);
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//
//        jedisConnectionFactory.setHostName("192.168.1.231");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setPassword("dPiv9fdwNeUQ1kzv");
//        jedisConnectionFactory.setDatabase(11);
//        jedisConnectionFactory.setPoolConfig(new JedisPoolConfig());
//
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate redisTemplate(){
//        RedisTemplate redisTemplate = new RedisTemplate();
//
//        redisTemplate.setConnectionFactory(new JedisConnectionFactory());
//        redisTemplate.setEnableTransactionSupport(true);
//
//        return redisTemplate;
//    }




}
