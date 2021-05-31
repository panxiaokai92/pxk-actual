package com.panxk.actual.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.pxk
 * @create: 2020-03-29
 **/
@RestController
public class RedisController {

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * redis分布式事务锁
     * 1、finally 释放锁，防止程序异常，锁无法释放
     * 2、setnx 设置过期时间，2个命令合并成一个命令（原子操作）
     * 3、分布式锁时间过期，逻辑没执行完，被别的线程释放锁？(锁失效场景)
     *   --> 赋值value = uuid, 判断当前执行uuid和redis中存储uuid，一直能释放锁
     * 4、redisson 解决过期时间超过，业务逻辑没有处理完，锁就释放了
     *   --> 开启分线程，timer锁续命，默认30s，1/3时间会去循环检测所是否释放，没有释放重新设置过期时间为30秒
     *   --> 底层实现：lua脚本,实现原子性操作
     * 5、redisson下，redis主从架构，set完key之后，master挂了，重新选择master，之前key丢失了，可以重新设置(无法保证强一致性)
     *   redis qps 10w+
     *   --> redlock实现,多步同步才成功，但是性能不好
     *   --> zookeeper实现分布式锁， 性能没有reids好
     * @return
     */

    @RequestMapping("/productStockSync")
    public String productStockSync(){

        //synchronized只能解决单进程下，多线程问题，并不能结局分布式下并发问题
        synchronized (this) {
            String redisKey = "stock";
            //stringRedisTemplate.opsForValue().set(redisKey, "100", 100, TimeUnit.SECONDS);
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(redisKey));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set(redisKey, realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        }
        return "成功";
    }

    @RequestMapping("/productStockRedis")
    public String productStockRedis(){

        String redisKey = "stock";
        String clientId = UUID.randomUUID().toString();

        try {
            //Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(redisKey, clientId);
            //stringRedisTemplate.expire(redisKey,30, TimeUnit.SECONDS);
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(redisKey, clientId, 30, TimeUnit.SECONDS);
            if (!result) {
                return "排队中...";
            }
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(redisKey));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set(redisKey, realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(redisKey))) {
                //释放锁
                stringRedisTemplate.delete(redisKey);
            }
        }
        return "成功";
    }

    @RequestMapping("/productStockRedisson")
    public String productStockRedisson(){

        String redisKey = "stock";
        RLock redissonLock = redisson.getLock(redisKey);
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(redisKey));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set(redisKey, realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            //释放锁
            redissonLock.unlock();
        }
        return "成功";
    }
}
