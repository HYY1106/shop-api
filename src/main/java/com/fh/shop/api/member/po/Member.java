package com.fh.shop.api.member.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data//不用生成get/set
@TableName("t_member")//数据库表名
public class Member implements Serializable {

    private Long id;
    private String memberName;//会员名
    private String realName;//真实姓名
    private String password;//密码
    //日期转化类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;//出生日期
    private String mail;//邮箱
    private  String phone;//手机号

    private Long shengId;//省id
    private Long shiId;//市id
    private Long xianId;//县id
    private String areaName;//地区名



}
