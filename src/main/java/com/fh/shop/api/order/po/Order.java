package com.fh.shop.api.order.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;
    private Long userId;//会员id
    private Date createTime;//创建日期
    private Date payTime;//支付日期
    private int status;//状态
    private BigDecimal totalPrice;//价格
    private int totalNum;//数量
    private int payType;//类型
    private Long recipientId;//收件人id
    private String recipientor;//收件人
    private String address;//地址
    private String phone;//手机号


}
