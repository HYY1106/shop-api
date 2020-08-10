package com.fh.shop.api.product.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.product.po.Product;

import java.util.List;

public interface ProductService {

    //热销
    ServerResponse findHotList();



    //克隆表达式
    List<Product>  findStockLessProductList();
}
