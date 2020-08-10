package com.fh.shop.api.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.member.po.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper//作用:在接口类上添加了@Mapper,在编译之后会生成相应的接口实现类      继承extends BaseMapper<Member>
@Repository//最好在持久层、业务层和控制层分别采用 @Repository用于标注数据访问组件
public interface MemberMapper extends BaseMapper<Member> {

    //添加
    @Insert("insert into t_member (memberName,password,realName,mail,phone,birthday,shengId,shiId,xianId,areaName) " +
            "values (#{memberName},#{password},#{realName},#{mail},#{phone},#{birthday},#{shengId},#{shiId},#{xianId},#{areaName})" )
    void addMember(Member member);
}
