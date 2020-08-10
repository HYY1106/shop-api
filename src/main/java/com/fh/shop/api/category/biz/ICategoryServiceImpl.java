package com.fh.shop.api.category.biz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.category.mapper.ICategoryMapper;
import com.fh.shop.api.category.po.Category;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class ICategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryMapper categoryMapper;


    @Override
    public List<Category> queryCategory() {
        return categoryMapper.queryCategory();
    }



    @Override
    public ServerResponse findCategoryList(){
        String cateListJson = RedisUtil.get("cateList");
        if (StringUtils.isNotEmpty(cateListJson)){
            List<Category> categories = JSONArray.parseArray(cateListJson,Category.class);
            RedisUtil.expire("cateList",1*60);
            return ServerResponse.success(categories);
        }
        List<Category> categories = categoryMapper.selectList(null);
        String cateArrJson = JSONObject.toJSONString(categories);
        RedisUtil.setEx("cateList",cateArrJson,1*60);
        return ServerResponse.success(categories);
    }
}
