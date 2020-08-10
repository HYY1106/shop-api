package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.mapper.MemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.rabbitmq.MQSender;
import com.fh.shop.api.rabbitmq.MailMessage;
import com.fh.shop.api.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

//而用 @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
@Service//用于标注业务层组件,这个注解是写在类上面的，标注将这个类交给Spring容器管理，spring容器要为他创建对象
@Transactional(rollbackFor = Exception.class)//实质是使用了JDBC 的事务来进行事务控制的,基于 Spring 的动态代理的机制
public class MemberServiceImpl implements MemberService{

    //私有化mapper层(@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private MemberMapper memberMapper;


    //私有化mapper层(@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private MailUtil mailUtil;//发送邮件

    @Autowired
    private MQSender mqSender;


    //添加
    @Override
    public ServerResponse addMember(Member member) {

        //验证逻辑
        //非空判断
        String memberName =  member.getMemberName();
        String password =  member.getPassword();
        String mail =  member.getMemberName();
        String phone =  member.getMemberName();
        if (StringUtils.isEmpty(memberName) || StringUtils.isEmpty(password)
                                            || StringUtils.isEmpty(mail)
                                            || StringUtils.isEmpty(phone)){
            return ServerResponse.error(ResponseEnum.REG_Member_Is_NULL);
        }
        //判断会员名是否唯一
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("memberName",memberName);
        Member memberDB = memberMapper.selectOne(queryWrapper);
        if (memberDB != null){
            return ServerResponse.error(ResponseEnum.REG_MemberName_Exist);
        }
        //判断手机号是否唯一
        QueryWrapper<Member> phoneQueryWrapper = new QueryWrapper<>();
        phoneQueryWrapper.eq("phone",phone);
        Member member1 = memberMapper.selectOne(phoneQueryWrapper);
        if (member1 != null){
            return ServerResponse.error(ResponseEnum.REG_MemberPhone_Exist);
        }
        //判断邮箱是否唯一
        QueryWrapper<Member> mailQueryWrapper = new QueryWrapper<>();
        mailQueryWrapper.eq("mail",mail);
        Member member2 = memberMapper.selectOne(mailQueryWrapper);
        if (member2 != null){
            return ServerResponse.error(ResponseEnum.REG_MemberMail_Exist);
        }

        //注册会员
        memberMapper.addMember(member);

        //给注册的会员发邮件
        mailUtil.sendMail(mail,"注册成功","恭喜"+member.getRealName()+"注册成功！！！");
        return ServerResponse.success();
    }


             //会员名下拉框
             @Override
             public ServerResponse validateMemberName(String memberName) {
                 if (StringUtils.isEmpty(memberName)){
                     return ServerResponse.error(ResponseEnum.REG_Member_Is_NULL);
                 }
                 //进行唯一验证
                 QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                 queryWrapper.eq("memberName",memberName);
                 Member memberDB = memberMapper.selectOne(queryWrapper);
                 if (memberDB != null){
                     return ServerResponse.error(ResponseEnum.REG_MemberName_Exist);
                 }
                 return ServerResponse.success();
             }


             //手机号下拉框
             @Override
             public ServerResponse validatePhone(String phone) {
               if (StringUtils.isEmpty(phone)){
                   return ServerResponse.error(ResponseEnum.REG_Member_Is_NULL);
               }
                 //判断手机号是否唯一
                 QueryWrapper<Member> phoneQueryWrapper = new QueryWrapper<>();
                 phoneQueryWrapper.eq("phone",phone);
                 Member member1 = memberMapper.selectOne(phoneQueryWrapper);
                 if (member1 != null){
                     return ServerResponse.error(ResponseEnum.REG_MemberPhone_Exist);
                 }
                 return ServerResponse.success();
             }

             //邮箱下拉框
             @Override
             public ServerResponse validateMail(String mail) {
                 if (StringUtils.isEmpty(mail)){
                     return ServerResponse.error(ResponseEnum.REG_Member_Is_NULL);
                 }
                 QueryWrapper<Member> mailQueryWrapper = new QueryWrapper<>();
                 mailQueryWrapper.eq("mail",mail);
                 Member member2 = memberMapper.selectOne(mailQueryWrapper);
                 if (member2 != null){
                     return ServerResponse.error(ResponseEnum.REG_MemberMail_Exist);
                 }
                 return ServerResponse.success();
             }





             //基于token登录
             @Override
             public ServerResponse login(String memberName, String password) {
                    //先解密
                  memberName = RSAUtil.decrypt(memberName);
                  password = RSAUtil.decrypt(password);

                 //==============1、进行验证============
                 //进行非空验证
                    if (StringUtils.isEmpty(memberName) || StringUtils.isEmpty(password)){
                        return ServerResponse.error(ResponseEnum.LOGIN_MEMBER_IS_EMPTY);
                    }
                 //判断用户名是否存在
                    QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("memberName",memberName);
                    Member member =  memberMapper.selectOne(queryWrapper);
                    if (member == null){
                        return ServerResponse.error(ResponseEnum.LOGIN_MEMBER_NAME_IS_ERROR);
                    }
                 //判断密码是否正确
                    if (!password.equals(member.getPassword())){
                        return ServerResponse.error(ResponseEnum.LOGIN_MEMBER_PASSWORD_IS_ERROR);
                    }
                             //生成token
                 //===========2、响应信息给客户端=========
                 //模拟JWT[Json Web Token]
                 //生成token样子类似于xxx.yyy用户信息,对用户信息的签名
                 //签名的目的:保证用户信息不被篡改
                 //怎么生成签名:md5(用户信息 结合 秘钥)
                 //sign代表签名 secret/secretKey代表秘钥
                 //秘钥是在服务端保存的,黑客,攻击者它们获取不到


                 //生成用户信息对应的json
                    MemberVo memberVo = new MemberVo();
                    Long memberId = member.getId();
                    memberVo.setId(memberId);
                    memberVo.setMemberName(member.getMemberName());
                    memberVo.setRealName(member.getRealName());
                    String uuid =  UUID.randomUUID().toString();
                    memberVo.setUuid(uuid);

                     // 将数据转换为字符串[将java对象转为json格式的字符串]
                     String memberJson =  JSONObject.toJSONString(memberVo);
                     //对用户信息进行base64编码
                     //[起到了一定的安全作用]
                     //(对于计算机小白来说,唬它了一下,但是对于计算机专业人事来说,起不到任何作用,可以直接将base64解码)
                     //jdk1.8内部直接提供了base64的工具类,如果jdk的版本低于1.8,就需要使用第三方来完成base64编码
                     try {
                     // 通过base64进行编码
                     String base64MemberJson = Base64.getEncoder().encodeToString(memberJson.getBytes("utf-8"));
                      // 结合 秘钥 生成 数据信息 对应的签名【保证数据不被篡改】
                     String sign =  Md5Util.sign(base64MemberJson,Md5Util.SECRET);
                     //对签名也进行base64编码
                     String base64Sign = Base64.getEncoder().encodeToString(sign.getBytes("utf-8"));
                     // 将明文和签名结合起来 [模拟jwt：Json Web Token]
                     //String result = base64MemberJson+"."+base64Sign;
                     //在redis中设置会员的过期时间【为了模拟session的过期时间效果】
                     RedisUtil.setEx((KeyUtil.buildMemberKey(memberId,uuid)),"",KeyUtil.MEMBER_EXPIRE);

                     //发送邮件 消息中间件 同步
                     String mail =  member.getMail();
                     //mailUtil.sendMail(mail,"注册成功",member.getRealName()+"在"+ DateUtil.date2str(new Date(),DateUtil.FULL_TIME)+"登录了!!!");

                         //消息中间件 异步
                         MailMessage mailMessage = new MailMessage();
                         mailMessage.setMail(mail);
                         mailMessage.setTitle("登录成功!!");
                         mailMessage.setRealName(member.getRealName());
                         mailMessage.setContent(member.getRealName()+"在"+ DateUtil.date2str(new Date(),DateUtil.FULL_TIME)+"登录了!!!");
                         mqSender.sendMailMessage(mailMessage);

                     // 响应给客户端
                     return ServerResponse.success(base64MemberJson+"."+base64Sign);
                 } catch (UnsupportedEncodingException e) {
                     e.printStackTrace();
                     //throw new RuntimeException(e);
                         return ServerResponse.error();
                 }
              }
         }
