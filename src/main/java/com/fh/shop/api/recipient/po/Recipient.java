package com.fh.shop.api.recipient.po;

import lombok.Data;

import java.io.Serializable;

@Data//不用生成get/set   实现implements Serializable
public class Recipient implements Serializable {

    private Long id;
    private String recipientor;//收件人
    private String address;//地址
    private String phone;//手机号
    private String mail;//邮箱
    private Long memberId;//会员id
    private int status;//状态 0代表被默认,1代表默认

}
