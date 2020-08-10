package com.fh.shop.api.brand.biz;

import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResponse;

public interface BrandService {

    //添加
    ServerResponse addBrand(Brand brand);
    //删除
    ServerResponse deleteBrand(Long id);
    //修改
    ServerResponse updateBrand(Brand brand);
    //批量删除
    ServerResponse deleteBatch(String ids);

    //测试类
    ServerResponse findList();

}
