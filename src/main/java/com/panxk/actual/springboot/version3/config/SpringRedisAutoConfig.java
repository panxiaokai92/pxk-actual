package com.panxk.actual.springboot.version3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: JavaConfig版本的装配， 等同于spring-redis.xml
 * @author: Mr.pxk
 * @create: 2020-04-08
 **/
@Configuration
//@PropertySource(value = {"classpath:rediscfg.properties"})
public class SpringRedisAutoConfig {

//    @Bean
//    public RedisCfgProperties redisCfgProperties(){
//        RedisCfgProperties redisCfgProperties = new RedisCfgProperties();
//        return redisCfgProperties;
//    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(30);
        jedisPoolConfig.setMaxIdle(18);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.setHostName("192.168.1.231");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setPassword("dPiv9fdwNeUQ1kzv");
        jedisConnectionFactory.setDatabase(11);
        jedisConnectionFactory.setPoolConfig(new JedisPoolConfig());

        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(new JedisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);

        return redisTemplate;
    }




}
