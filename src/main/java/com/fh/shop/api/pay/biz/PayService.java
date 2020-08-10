package com.fh.shop.api.pay.biz;

import com.fh.shop.api.common.ServerResponse;

public interface PayService {

    //统一下单
    ServerResponse createNative(Long memberId);
    //查询支付状态
    ServerResponse queryStatus(Long memberId);
}
