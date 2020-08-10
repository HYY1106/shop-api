package com.fh.shop.api.job;

import com.fh.shop.api.product.biz.ProductService;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


//克隆表达式
@Component//就是说把这个类交给Spring管理，因为不清楚这个类是属于哪个层面，所以就用@Component。
public class StockJob {

    //私有化service层 (@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Resource(name="productService")
    private ProductService productService;

    //私有化service层 (@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private MailUtil mailUtil;



    //该参数接收一个cron表达式，cron表达式是一个字符串，字符串以5或6个空格隔开，分开共6或7个域，每一个域代表一个含义
    //@Scheduled定时任务
    @Scheduled(cron = "0/5 *  * * * ?")
    public void checkStock(){

        //获取库存不足的商品列表
      List<Product> stockLessProductList =  productService.findStockLessProductList();
      //生成表格
        StringBuffer productHtml = new StringBuffer();
        productHtml.append("<table bgcolor=\"#ffc0cb\" cellpadding=\"0\" cellspacing=\"0\" border=\"1px\" width=\"500px\">\n" +
                "   <thead>\n" +
                "      <tr>\n" +
                "          <th>商品名</th>\n" +
                "          <th>商品价格</th>\n" +
                "          <th>商品库存</th>\n" +
                "      </tr>\n" +
                "   </thead>\n" +
                "    <tbody>");
        for (Product product : stockLessProductList){
            productHtml.append("<tr>\n" +
                    "          <th>"+product.getProductName()+"</th>\n" +
                    "          <th>"+product.getPrice().toString()+"</th>\n" +
                    "          <th>"+product.getStock()+"</th>\n" +
                    "      </tr>");
        }
        productHtml.append("</tbody>\n" +
                "</table>");

       String tableHtml = productHtml.toString();
       //发送邮件
       //mailUtil.sendMail("3452603469@qq.com","库存不足提醒",tableHtml);
    }



}
