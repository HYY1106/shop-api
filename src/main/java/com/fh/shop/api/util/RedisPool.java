package com.fh.shop.api.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//连接池
public class RedisPool {

    private RedisPool(){

    }

    //redis连接池
    private static JedisPool jedisPool;

    private static void  initPool(){
        //连接处配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(1000);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(500);
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(500);
        //测试是否能正常连接
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
        //给  jedisPool     赋值       配置               linux IP地址     redis端口号
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.9.128",7020);
    }

    //只会在jvm加载类，执行一次！！保证只会创建一个连接池【连接池单例】
    //static静态块中只能调用静态的static方法
    static {
        initPool();
    }


    public static Jedis getResources(){
       return jedisPool.getResource();
    }
}
