package com.fh.shop.api.util;


import redis.clients.jedis.Jedis;

public class RedisUtil {

    //续命
    public static  void expire(String key,int seconds){
        Jedis jedis = null;
        try {
            jedis =  RedisPool.getResources();
            jedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }



    //boolean类型
    public static  boolean exist(String key){
        Jedis jedis = null;
        try {
            jedis =  RedisPool.getResources();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }




    //基于token登录  模拟session时间效果
    public static void setEx(String key,String value,int seconds){
        Jedis resources = null;
        try {
            resources = RedisPool.getResources();
            resources.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != resources){
                resources.close();
            }
        }
    }


    public static  void delete(String key){

        Jedis jedis = null;
        try {
            jedis =  RedisPool.getResources();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static void set(String key,String value) {

        Jedis jedis = null;
        try {
            jedis = RedisPool.getResources();
            jedis.set(key, value);
        } catch (Exception e) {
            //打印
            e.printStackTrace();
            //抛出异常
            throw new RuntimeException(e);
        }finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }


   public static String get(String key){

       Jedis jedis = null;
       try {
           jedis = RedisPool.getResources();
           String ss = jedis.get(key);
           return ss;
       } catch (Exception e) {
           //打印
           e.printStackTrace();
           //抛出异常
           throw new RuntimeException(e);
       }finally {
           if (null != jedis){
               jedis.close();
           }
       }
   }
}
