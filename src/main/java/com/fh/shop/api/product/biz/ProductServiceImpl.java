package com.fh.shop.api.product.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.mapper.ProductMapper;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.product.vo.ProductVo;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
                           //而用 @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
@Service("productService")//用于标注业务层组件,这个注解是写在类上面的，标注将这个类交给Spring容器管理，spring容器要为他创建对象
public class ProductServiceImpl implements ProductService{

    //私有化mapper层(@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private ProductMapper productMapper;

    //克隆表达式
    @Value("${stock.less}")
    private Long stockLess;

    //热销
    @Override
    public ServerResponse findHotList() {
        String hotProductList = RedisUtil.get("hotProductList");
        if (StringUtils.isNotEmpty(hotProductList)){
            //将json格式的字符串转换为java对象
            //[如果要转换为java中的集合则用parseArray]
            //[如果要转换为java中的对象则用parseObject]

          List<ProductVo> productList =  JSONObject.parseArray(hotProductList,ProductVo.class);
          return ServerResponse.success(productList);
        }

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","productName","price","mainImagePath");
        queryWrapper.eq("isHot",1);
        queryWrapper.eq("status",1);
        List<Product> productList = productMapper.selectList(queryWrapper);

        //po转vo
        ArrayList<ProductVo> productVoList = new ArrayList<>();
        for (Product product : productList) {
            ProductVo productVo = new ProductVo();
            productVo.setId(product.getId());
            productVo.setProductName(product.getProductName());
            productVo.setPrice(product.getPrice().toString());
            productVo.setMainImagePath(product.getMainImagePath());
            productVoList.add(productVo);

        }
        //往缓存中存一份
        //把java对象转换为json格式的字符串
        String hotProductListJson = JSONObject.toJSONString(productVoList);
        RedisUtil.set("hotProductList",hotProductListJson);
        return ServerResponse.success(productVoList);
    }


    //克隆表达式
    @Override
    public List<Product> findStockLessProductList() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("stock",stockLess);
        List<Product> productList = productMapper.selectList(queryWrapper);
        return productList;
    }
}
