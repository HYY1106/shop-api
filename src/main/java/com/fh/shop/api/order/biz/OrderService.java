package com.fh.shop.api.order.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.order.param.OrderParam;

public interface OrderService {

    //生成确认订单页面
    ServerResponse generateConfirmOrder(Long memberId);
    //生成订单
    ServerResponse generateOrder(OrderParam orderParam);
    //创建订单
    void createOrder(OrderParam orderParam);
    //获取订单的结果
    ServerResponse getResult(Long memberId);

    //生成订单 作废
    //ServerResponse generateOrder(OrderParam orderParam);
}
