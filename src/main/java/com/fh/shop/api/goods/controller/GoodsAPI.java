package com.fh.shop.api.goods.controller;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.goods.biz.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/goods")
@Api(tags = "商品接口")
public class GoodsAPI {


    @Resource(name="goodsService")
    private GoodsService goodsService;

    @RequestMapping(value = "/findHotProductList",method = RequestMethod.GET)
    @ResponseBody
    //@Secure
    @ApiOperation("查询热销商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nonce",value = "唯一的随机数",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "time",value = "发送请求的时间",dataType = "string",required = true,paramType = "header"),
            @ApiImplicitParam(name = "sign",value = "签名",dataType = "string",required = true,paramType = "header")
    })
    public ServerResponse findHotProductList(){
        return goodsService.findHotProductList();
    }
}
