package com.fh.shop.api.common;

public enum ResponseEnum {

    PAY_IS_PAIL(5000,"支付失败,超时了"),

    ORDER_IS_ERROR(4002,"下单失败"),
    ORDER_IS_QUEUE(4001,"订单正在排队"),
    ORDER_STOCK_LESS(4000,"下订单时,库存不足"),


    CART_DELETE_BATCH_IDS_IS_NULL(3003,"批量删除时ids必须传递"),
    CARR_NUM_IS_ERROR(3002,"商品里的数量不合法"),
    CART_PRODUCT_IS_DOWN(3001,"商品下架了"),
    CART_PRODUCT_IS_NULL(3000,"添加的商品不存在"),

    LOGIN_TIME_OUT(9006,"登录超时"),
    LOGIN_MEMBER_IS_CHANGE(9005,"会员信息被篡改"),
    LOGIN_HEADER_CONTENT_IS_MISS(9004,"头信息不完整"),
    LOGIN_HEADER_IS_MISS(9003,"头信息丢失"),
    LOGIN_MEMBER_PASSWORD_IS_ERROR(9002,"登录时密码错误"),
    LOGIN_MEMBER_NAME_IS_ERROR(9001,"登录时会员名错误"),
    LOGIN_MEMBER_IS_EMPTY(9000,"登上时会员名或者密码为空"),


    REG_MemberName_Exist(1001,"会员名称已存在"),
    REG_MemberMail_Exist(1002,"会员邮箱已存在"),
    REG_MemberPhone_Exist(1003,"会员手机号已存在"),
    REG_Member_Is_NULL(1000,"会员信息不能为空");

    private int code;
    private String msg;

    private ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
