package com.fh.shop.api.category.biz;

import com.fh.shop.api.category.po.Category;
import com.fh.shop.api.common.ServerResponse;

import java.util.List;

public interface ICategoryService {

    List<Category> queryCategory();



    //查看数据
    ServerResponse findCategoryList();
}
