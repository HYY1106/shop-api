package com.fh.shop.api.cart.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fh.shop.api.util.BigDecimalJackson;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartItem implements Serializable {

    private Long goodsId;
    private String goodsName;//购物车
    @JsonSerialize(using = BigDecimalJackson.class)
    private BigDecimal price;//价格
    private int num;//数量
    @JsonSerialize(using = BigDecimalJackson.class)
    private BigDecimal subPrice;//小计
    private String imageUrl;//图片


}
