package com.fh.shop.api.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//rabbitMq配置类
@Configuration
public class MQConfig {

    //发送邮件
    //创建交换机Exchange
    public static final String MAILEXCHANGE = "mailExchange";
    //创建队列  MQ[message queue]
    public static final String MAILQUEUE = "mailQueue";
    //将队列和交互机绑定,并指明路由key
    public static final String MAILROUTEKEY = "mail";

    //Fanout
    //创建交换机Exchange
    public static final String GOODS_FANOUT_EXCHANGE = "goodsFanoutExchange";
    //创建队列
    public static final String GOODS_QUEUE1 = "goodsQueue1";
    //创建队列
    public static final String GOODS_QUEUE2 = "goodsQueue2";

    //Topic
    //创建交换机Exchange
    public static final String GOODS_TOPIC_EXCHANGE = "goodsTopicExchange";
    //创建队列
    public static final String GOODS_TOPIC_QUEUE1 = "goodsTopicQueue1";
    //创建队列
    public static final String GOODS_TOPIC_QUEUE2 = "goodsTopicQueue2";
    //创建队列
    public static final String GOODS_TOPIC_QUEUE3 = "goodsTopicQueue3";

    //生成订单
    //创建交换机Exchange
    public static final String ORDEREXCHANGE = "orderExchange";
    //创建队列
    public static final String ORDERQUEUE = "orderQueue";
    //将队列和交互机绑定,并指明路由key
    public static final String ORDERROUTEKEY = "order";

    //品牌队列
    //public static final String BRAND_QUEUE = "brand-queue";

    //死信队列
    //public static final String GOODS_EXCHANGE_DLX = "goodsExchangeDLX";
    //public static final String GOODS_QUEUE_DLX = "goods-queue-dlx";





    //创建交换机
    @Bean
    public DirectExchange mailExchange(){//交换机名字    持久化         是否自动删除
        return new DirectExchange(MAILEXCHANGE,true,false);
    }
    //创建队列
    @Bean
    public Queue mailQueue(){//交换机名字   持久化
       return new Queue(MAILQUEUE,true);
    }
    //将队列和交互机绑定
    @Bean
    public Binding mailBinding(){   //队列             交换机            并且指明路由的key
         return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAILROUTEKEY);
    }


    //Fanout 创建交换机
    @Bean
    public FanoutExchange goodsFanoutExchange(){
        return new FanoutExchange(GOODS_FANOUT_EXCHANGE,true,false);
    }
    //创建队列1
    @Bean
    public Queue goodsQueue1(){
        return new Queue(GOODS_QUEUE1,true);
    }
    //创建队列2
    @Bean
    public Queue goodsQueue2(){
        return new Queue(GOODS_QUEUE2,true);
    }
    //将队列1和交互机绑定
    @Bean
    public Binding goodsBinding1(){
        return BindingBuilder.bind(goodsQueue1()).to(goodsFanoutExchange());
    }
    //将队列2和交互机绑定
    @Bean
    public Binding goodsBinding2(){
        return BindingBuilder.bind(goodsQueue2()).to(goodsFanoutExchange());
    }


    //Topic 创建交换机
    @Bean
    public TopicExchange goodsTopicExchange(){
        return new TopicExchange(GOODS_TOPIC_EXCHANGE,true,false);
    }
    //创建队列1
    @Bean
    public Queue goodsTopicQueue1(){
        return new Queue(GOODS_TOPIC_QUEUE1,true);
    }
    //创建队列2
    @Bean
    public Queue goodsTopicQueue2(){
        return new Queue(GOODS_TOPIC_QUEUE2,true);
    }
    //创建队列3
    @Bean
    public Queue goodsTopicQueue3(){
        return new Queue(GOODS_TOPIC_QUEUE3,true);
    }
    //将队列1和交互机绑定
    @Bean
    public Binding goodsTopicBinding1(){
        return BindingBuilder.bind(goodsTopicQueue1()).to(goodsTopicExchange()).with("*.info.*");
    }
    //将队列2和交互机绑定
    @Bean
    public Binding goodsTopicBinding2(){
        return BindingBuilder.bind(goodsTopicQueue2()).to(goodsTopicExchange()).with("a.b.*");
    }
    //将队列3和交互机绑定
    @Bean
    public Binding goodsTopicBinding3(){
        return BindingBuilder.bind(goodsTopicQueue3()).to(goodsTopicExchange()).with("#");
    }


    //生成订单
    //创建交换机
    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange(ORDEREXCHANGE,true,false);
    }
    //创建队列
    @Bean
    public Queue orderQueue(){
        return new Queue(ORDERQUEUE,true);
    }
    //将队列和交互机绑定,并指明路由key
    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDERROUTEKEY);
    }



    //品牌队列
    /*@Bean
    public Queue brandQueue(){
        return new Queue(BRAND_QUEUE,true);
    }

    //死信队列
    @Bean
    public TopicExchange goodsExchangeDLX(){
        return new TopicExchange(GOODS_EXCHANGE_DLX,true,false);
    }
    @Bean
    public Queue goodsQueueDLX(){
        return new Queue(GOODS_QUEUE_DLX,true);
    }
    @Bean
    public Binding goodsQueueDLXBinding(){
        return BindingBuilder.bind(goodsQueueDLX()).to(goodsExchangeDLX()).with("#");
    }*/



}
