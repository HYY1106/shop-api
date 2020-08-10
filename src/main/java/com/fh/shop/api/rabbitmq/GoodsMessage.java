package com.fh.shop.api.rabbitmq;

import lombok.Data;

import java.io.Serializable;

@Data//不用生成get/set
public class GoodsMessage implements Serializable {


    private Long id;
    private String price;//商品价格
    private Long stock;//商品库存

}
