package com.fh.shop.api.product.controller;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.biz.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController//相当于controller+responsebody,controller用于标注控制层组件
@RequestMapping("/api/product")//请求路径
//@CrossOrigin("*")//解决跨域问题
public class ProductController {

    //私有化service层 (@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Resource(name="productService")
    private ProductService productService;


    //热销
    @GetMapping//处理get请求
    //@Check
    public ServerResponse findHotList(){
        return productService.findHotList();
    }
}
