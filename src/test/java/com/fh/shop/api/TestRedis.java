package com.fh.shop.api;

import com.fh.shop.api.util.RedisUtil;
import org.junit.jupiter.api.Test;


public class TestRedis {


    @Test
    public void test1(){
        /*RedisUtil.set("username","hanhaoyang");
        String username = RedisUtil.get("username");
        System.out.println(username);*/

        RedisUtil.delete("userName");
    }
}
