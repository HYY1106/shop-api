package com.fh.shop.api.member.biz;


import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.po.Member;

public interface MemberService {

    //添加
    ServerResponse addMember(Member member);
    //会员名下拉框
    ServerResponse validateMemberName(String memberName);
    //手机号下拉框
    ServerResponse validatePhone(String phone);
    //邮箱下拉框
    ServerResponse validateMail(String mail);



    //基于token登录
    ServerResponse login(String memberName, String password);
}
