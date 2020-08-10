package com.fh.shop.api;

import com.fh.shop.api.brand.biz.BrandService;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.rabbitmq.MQSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest//测试类
public class ShopApiApplicationTests {

    @Resource(name="brandService")
    private BrandService brandService;



    @Test
    public void contextLoads() {
    }


    @Test
    public void testSend(){
       ServerResponse list =  brandService.findList();
       System.out.println(list);
    }


    //单元测试
    @Autowired
    private MQSender mqSender;
    //发邮件
    @Test
    public void testSendMsg(){
        mqSender.sendMail("你好!!!");
    }

    //Fanout
    @Test
    public void test2(){
        mqSender.sendMsgl("韩浩阳阳!!!");
    }

    //Topic
    @Test
    public void test3(){
        mqSender.sendMsg2("明明你也很爱我!!!");
    }


}
