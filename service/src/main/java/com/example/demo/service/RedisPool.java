package com.example.demo.service; /******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujh
 * @version V1.0
 * @Title: RedisPool.java
 * @Package com.example.demo.service
 * @Description
 * @date 2019 06-13 22:52.
 */
public class RedisPool {
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        List<JedisShardInfo> list = new ArrayList<>(2);
        list.add(new JedisShardInfo("172.21.107.26", 6379));
        list.add(new JedisShardInfo("172.21.107.26", 6380));
        pool = new ShardedJedisPool(config, list);
    }
    protected static ShardedJedisPool pool;

    public static ShardedJedis getResource(){
        return pool.getResource();
    }

}
