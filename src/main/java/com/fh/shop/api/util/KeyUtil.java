package com.fh.shop.api.util;

public class KeyUtil {



    //存入redis中【为了模拟session的过期时间效果】
    public static String buildMemberKey(Long memberId, String uuid) {
        return "member:"+memberId+":"+uuid;
    }

    //设置过期时间
    public static final int MEMBER_EXPIRE = 30*60;

    //如果会员已经有了对应的购物车
    public static String buildCartKey(Long memberId){
        return "cart:"+memberId;
    }

    //提醒库存不足
    public static  String buildStockLessKey(Long memberId){
        return "order:stock:less:"+memberId;
    }

    //提交订单成功
    public static  String buildOrderKey(Long memberId){
        return "order:"+memberId;
    }

    //将支付日志存入redis
    public static  String buildPayLogKey(Long memberId){
        return "paylog:"+memberId;
    }
    //下订单失败
    public static  String buildOrderErrorKey(Long memberId){
        return "order:error:"+memberId;
    }
}
