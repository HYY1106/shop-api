package com.fh.shop.api.rabbitmq;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandMessage implements Serializable {

    private String id;
    private String brandName;//品牌名称
}
