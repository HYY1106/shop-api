package com.fh.shop.api.brand.mapper;

import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandMapper {

    //添加
    @Insert("insert into t_brand (brandName) values (#{brandName})")
    void addBrand(Brand brand);

    //删除
    @Delete("delete from t_brand where id")
    void deleteBrand(Long id);

    //查询
    @Select("select * from t_brand")
    ServerResponse findList();

    //修改
    @Update("update t_brand set brandName=#{brandName} where id=#{id}")
    void updateBrand(Brand brand);

    //批量删除
    void deleteBatch(String ids);
}
