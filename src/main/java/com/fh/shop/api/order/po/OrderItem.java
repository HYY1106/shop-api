package com.fh.shop.api.order.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("t_order_detail")
public class OrderItem implements Serializable {

    private Long id;
    private String orderId;//订单id
    private Long userId;//会员id
    private Long productId;//商品id
    private String productName;//商品名
    private String imageUrl;//图片
    private BigDecimal price;//单价
    private int num;//数量
    private BigDecimal subPrice;//小计

}
