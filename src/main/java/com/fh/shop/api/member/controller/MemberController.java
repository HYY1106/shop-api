package com.fh.shop.api.member.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemConstant;
import com.fh.shop.api.member.biz.MemberService;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.util.KeyUtil;
import com.fh.shop.api.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController//相当于controller+responsebody,controller用于标注控制层组件
@RequestMapping("/api/member")//请求路径
@Api(tags = "会员接口")
//@CrossOrigin("*")//解决跨域问题
public class MemberController {

    //私有化service层 (@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private MemberService memberService;


    //添加
    @PostMapping//处理post请求
    @ApiOperation("会员注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName",value = "会员名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "password",value = "密码",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "realName",value = "真实姓名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "birthday",value = "出生日期",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "mail",value = "邮箱",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "phone",value = "手机号",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shengId",value = "省id",type = "long",required = false,paramType = "query"),
            @ApiImplicitParam(name = "shiId",value = "市id",type = "long",required = false,paramType = "query"),
            @ApiImplicitParam(name = "xianId",value = "县id",type = "long",required = false,paramType = "query"),
            @ApiImplicitParam(name = "areaName",value = "地区名",type = "string",required = false,paramType = "query")

    })
    public ServerResponse addMember(Member member){
        return memberService.addMember(member);
    }

    //会员名下拉框
    @GetMapping("/validateMemberName")//处理get请求
    public ServerResponse validateMemberName(String memberName){
        return memberService.validateMemberName(memberName);
    }
    //手机号下拉框
    @GetMapping("/validatePhone")
    public ServerResponse validatePhone(String phone){
        return memberService.validatePhone(phone);
    }
    //邮箱下拉框
    @GetMapping("/validateMail")
    public ServerResponse validateMail(String mail){
        return memberService.validateMail(mail);
    }




    //基于token登录
    @PostMapping("login")
    @ApiOperation("会员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName",value = "会员名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "password",value = "密码",type = "string",required = true,paramType = "query")
    })
    public ServerResponse login(String memberName, String password){
        return memberService.login(memberName,password);
    }




    //导航栏注册会员
    @GetMapping("/findMember")
    @Check
    @ApiOperation("获取会员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth",value = "头信息",type = "string",required = true,paramType = "header")
    })
    public ServerResponse findMember(HttpServletRequest request){
        MemberVo member =  (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        return ServerResponse.success(member);
    }


    //退出
    @GetMapping("/logout")
    @Check
    public ServerResponse logout(HttpServletRequest request){
        MemberVo member =  (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
        String uuid = member.getUuid();
        //清楚redis中的数据
        RedisUtil.delete(KeyUtil.buildMemberKey(memberId,uuid));
        return ServerResponse.success();
    }

}
