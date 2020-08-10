package com.fh.shop.api.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    //如果商品存在更新商品的数量和小计,更新购物车[总个数,总计]
    public static BigDecimal mul(String s1,String s2){
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return  b1.multiply(b2).setScale(2);
    }


    //更新购物车
    public static BigDecimal add(String s1,String s2){
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return  b1.add(b2);
    }
}
