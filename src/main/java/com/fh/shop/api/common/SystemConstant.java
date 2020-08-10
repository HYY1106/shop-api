package com.fh.shop.api.common;

public class SystemConstant {


    //存入request中,供其后续使用
    public static final String CURR_MEMBER = "user";
    //商品的状态是否正常
    public static final int PRODUCT_IS_DOWN = 0;

    //未支付
    public interface OrderStatus{
        int WAIT_PAY = 10;
        int PAY_SUCCESS = 20;
        int SEND_GOODS = 30;
    }


    //状态
    public interface PayStatus{
        int WAIT_PAY = 10;
        int PAY_SUCCESS = 20;
    }
}
