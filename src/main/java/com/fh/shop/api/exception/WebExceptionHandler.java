package com.fh.shop.api.exception;

import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//统一异常处理
public class WebExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public ServerResponse handGlobalException(GlobalException e){
       ResponseEnum responseEnum = e.getResponseEnum();
       return ServerResponse.error(responseEnum);
    }
}
