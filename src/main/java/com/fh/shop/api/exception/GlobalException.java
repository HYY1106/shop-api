package com.fh.shop.api.exception;

import com.fh.shop.api.common.ResponseEnum;

//继承extends RuntimeException 自定义异常
public class GlobalException extends RuntimeException{

    //私有化枚举
    private ResponseEnum responseEnum;

    //有参
    public GlobalException(ResponseEnum responseEnum){
        this.responseEnum = responseEnum;
    }

    //get
    public ResponseEnum getResponseEnum(){
        return responseEnum;
    }
}
