package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.AreaService;
import com.fh.shop.api.common.ServerResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController//相当于controller+responsebody,controller用于标注控制层组件
@RequestMapping("/api/area")//请求路径
@Api(tags = "地区接口")
//@CrossOrigin("*")//解决跨域问题
public class AreaController {

    //私有化service层 (@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Resource(name="areaService")
    private AreaService areaService;



    @GetMapping//处理get请求
    public ServerResponse findChilds(Long id){
     return areaService.findChilds(id);
    }


    /*@GetMapping
    @ApiOperation("根据id查找下面所有的地区列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "地区id",required = true,dataType = "long",paramType = "query")
    })
    public ServerResponse list(Long id){
        return areaService.findAreaList(id);
    }


    @GetMapping("/tree")
    @ApiOperation("查询地区的属性嵌套结构")
    public ServerResponse tree(){
        return areaService.findAreaTree();
    }*/
}
