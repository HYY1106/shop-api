package com.fh.shop.api.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.config.MQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//让springioc管理起来
public class MQSender {

    @Autowired
    public AmqpTemplate amqpTemplate;//简单的用这个
    @Autowired
    public RabbitTemplate rabbitTemplate;//【建议使用】

    public void sendMail(String info){
         amqpTemplate.convertAndSend(MQConfig.MAILEXCHANGE,MQConfig.MAILROUTEKEY,info);
     }


    //发送邮件
    public void sendMailMessage(MailMessage mailMessage){
       String mailJson = JSONObject.toJSONString(mailMessage);
        amqpTemplate.convertAndSend(MQConfig.MAILEXCHANGE,MQConfig.MAILROUTEKEY,mailJson);
    }


     //Fanout
     public void sendMsgl(String info){
       rabbitTemplate.convertAndSend(MQConfig.GOODS_FANOUT_EXCHANGE,"",info);
    }


    //Topic
    public void sendMsg2(String info){
        rabbitTemplate.convertAndSend(MQConfig.GOODS_TOPIC_EXCHANGE,"c.info.t",info);
    }

}
