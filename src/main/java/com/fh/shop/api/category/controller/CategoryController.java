package com.fh.shop.api.category.controller;

import com.fh.shop.api.category.biz.ICategoryService;
import com.fh.shop.api.category.po.Category;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api/cate")
public class CategoryController {

    @Resource(name = "categoryService")
    private ICategoryService categoryService;

    @GetMapping
    //@Check
    public ServerResponse queryCategoryList(){
        List<Category> list = categoryService.queryCategory();
        return  ServerResponse.success(list);
    }



    @GetMapping("/list")
    public ServerResponse findCategoryList(){
        return categoryService.findCategoryList();
    }
}
