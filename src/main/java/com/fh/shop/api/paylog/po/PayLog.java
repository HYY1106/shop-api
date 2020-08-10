package com.fh.shop.api.paylog.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_paylog")
public class PayLog implements Serializable {

    @TableId(value = "out_trade_no",type = IdType.INPUT)
    private String outTradeNo;
    private Long userId;//会员id
    private String orderId;//订单id
    private Date createTime;//创建时间
    private Date payTime;//支付时间
    private BigDecimal payMoney;//支付价格
    private int payType;//支付类型
    private int payStatus;//支付状态
    private String transactionId;

}
