/******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author liujh
 * @version V1.0
 * @Title: HelloWorldCommand.java
 * @Package com.example.demo.command
 * @Description
 * @date 2019 05-09 23:50.
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;

    public HelloWorldCommand(String name) {
        // 最少配置:指定命令组名(CommandGroup)
        //super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                    /* 配置依赖超时时间,500毫秒 */
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                                                          .withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑封装在run()方法中
        try {
            Thread.sleep(1000);
        }
        catch (Exception ex) {
            System.out.println("run error:" + ex.getMessage());
        }
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "Failed execute helloworld command";
    }
}
