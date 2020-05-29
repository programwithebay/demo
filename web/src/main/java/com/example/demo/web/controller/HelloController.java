/******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.controller;

import com.example.demo.service.RedisPool;
import com.example.demo.service.command.HelloWorldCommand;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.ShardedJedis;
import java.text.ParseException;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * @author liujh
 * @version V1.0
 * @Title: HelloController.java
 * @Package com.example.demo.controller
 * @Description
 * @date 2019 04-27 23:48.
 */
@RestController
public class HelloController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SimpleDateFormat dateFormat;


    //@RequestMapping("/hello")
    //public String hello() {
    //    return "hello,this is a springboot demo";
    //}

    @RequestMapping("hytrix")
    public void hytrix() {
        // 每个Command对象只能调用一次,不可以重复调用,
        // 重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        // 使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
        String result = helloWorldCommand.execute();
        System.out.println("result=" + result);

        helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        // 异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldCommand.queue();

        try {
            // get操作不能超过command定义的超时时间,默认:1秒
            result = future.get(100, TimeUnit.MILLISECONDS);
        }catch (Exception ex){
            ex.printStackTrace();
            return;
        }
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());
    }

    @RequestMapping("/redis")
    public String redis(){
        try {
            ShardedJedis conn = RedisPool.getResource();
            String value = conn.get("ok");
            conn.close();
            return value;
        }catch (Exception ex){
            return ex.getMessage();
        }
        //return "error";
    }

    //@RequestMapping("/spring_redis")
    //public String redis1(){
    //    final String value = stringRedisTemplate.opsForValue().get("keytest");
    //
    //    return value;
    //}

    @RequestMapping("/limit")
    public String testAcquire() {
        RateLimiter limiter = RateLimiter.create(1);
        StringBuilder sb =new StringBuilder();

        for(int i = 1; i < 10; i = i + 2 ) {
            double waitTime = limiter.acquire(i);
            sb.append("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime + "<br/>");
        }

        return sb.toString();
    }

    /**
     *
     * @return
     */
    @RequestMapping("/xml")
    public String customXml(){
        String date = "2010-10-10 11:12:14";

        SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date time = ymdhms.parse(date);
            String dateFormatStr = dateFormat.format(time);

            return dateFormatStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

}
