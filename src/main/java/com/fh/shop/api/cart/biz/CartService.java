package com.fh.shop.api.cart.biz;

import com.fh.shop.api.common.ServerResponse;

public interface CartService {

    //添加
    ServerResponse addItem(Long memberId,Long goodsId,int num);

    //查询
    ServerResponse findItemList(Long memberId);

    //购物车括号里的数量
    ServerResponse findItemCount(Long memberId);
    //删除
    ServerResponse deleteCartItem(Long memberId, Long goodsId);
    //批量删除
    ServerResponse deleteBatchItems(Long memberId, String ids);
}
