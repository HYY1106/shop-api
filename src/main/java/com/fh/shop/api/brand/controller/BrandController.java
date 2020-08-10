package com.fh.shop.api.brand.controller;

import com.fh.shop.api.brand.biz.BrandService;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.rabbitmq.MQSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController//相当于controller+responsebody,controller用于标注控制层组件
@RequestMapping("/api/brands")//请求路径
@Api(tags = "品牌操作相关的接口")
public class BrandController {

    //私有化service层 (@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Resource(name="brandService")
    private BrandService brandService;

    @Autowired
    private MQSender mqSender;

    //添加
    @PostMapping//处理post请求
    @ApiOperation("添加品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandName",value = "品牌名",type = "string",required = true,paramType = "query")
    })
    public ServerResponse addBrand(Brand brand){
        return brandService.addBrand(brand);
    }


    //查询
    @GetMapping("list")
    @ApiOperation("获取所有品牌列表")
    public ServerResponse list(){
        return brandService.findList();
    }

    //删除
    @DeleteMapping("/{brandId}")
    @ApiOperation("根据品牌id删除指定的品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandId",value = "品牌id",required = true,type = "long",paramType = "path")
    })
    public ServerResponse deleteBrand(@PathVariable("brandId") Long id){
        return brandService.deleteBrand(id);
    }

    //修改
    @PutMapping
    @ApiOperation("更新品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand",value = "品牌的json格式",required = true,type = "brand",paramType = "body")
    })
    public ServerResponse updateBrand(@RequestBody Brand brand){
        return brandService.updateBrand(brand);
    }

    //批量删除
    @DeleteMapping
    @ApiOperation("批量删除品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "品牌id集合,都好分隔,如1,2,3",type = "string",required = true,paramType = "query")
    })
    public ServerResponse deleteBatch(String ids){
        return brandService.deleteBatch(ids);
    }




}
