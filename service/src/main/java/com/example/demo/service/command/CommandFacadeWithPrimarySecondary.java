/******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.service.command;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author liujh
 * @version V1.0
 * @Title: CommandFacadeWithPrimarySecondary.java
 * @Package com.example.demo.command
 * @Description
 * @date 2019 05-10 0:19.
 */
public class CommandFacadeWithPrimarySecondary extends HystrixCommand<String> {
    private final static DynamicBooleanProperty usePrimary = DynamicPropertyFactory.getInstance()
                                                                                   .getBooleanProperty("primarySecondary.usePrimary",
                                                                                                       true);
    private final int id;

    public CommandFacadeWithPrimarySecondary(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("PrimarySecondaryCommand"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                                                          .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.id = id;
    }

    @Override
    protected String run() {
        if (usePrimary.get()) {
            return new PrimaryCommand(id).execute();
        } else {
            return new SecondaryCommand(id).execute();
        }
    }

    @Override
    protected String getFallback() {
        return "static-fallback-" + id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    private static class PrimaryCommand extends HystrixCommand<String> {
        private final int id;

        private PrimaryCommand(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(600)));
            this.id = id;
        }

        @Override
        protected String run() {
            // perform expensive 'primary' service call
            return "responseFromPrimary-" + id;
        }
    }

    private static class SecondaryCommand extends HystrixCommand<String> {
        private final int id;

        private SecondaryCommand(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("SecondaryCommand"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SecondaryCommand"))
                        .andCommandPropertiesDefaults(
                                                      // we default to a 100ms timeout for secondary
                                                      HystrixCommandProperties.Setter()
                                                                              .withExecutionIsolationThreadTimeoutInMilliseconds(100)));
            this.id = id;
        }

        @Override
        protected String run() {
            // perform fast 'secondary' service call
            return "responseFromSecondary-" + id;
        }
    }
}
