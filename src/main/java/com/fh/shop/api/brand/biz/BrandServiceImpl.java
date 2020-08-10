package com.fh.shop.api.brand.biz;

import com.fh.shop.api.brand.mapper.BrandMapper;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("brandService")//用于标注业务层组件,这个注解是写在类上面的，标注将这个类交给Spring容器管理，spring容器要为他创建对象
                        // 而用 @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
public class BrandServiceImpl implements BrandService{

    //私有化mapper层(@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private BrandMapper brandMapper;

    //添加
    @Override
    public ServerResponse addBrand(Brand brand) {
        brandMapper.addBrand(brand);
        return ServerResponse.success();
    }

    //查询
    @Override
    public ServerResponse findList() {
        return brandMapper.findList();
    }

    //删除
    @Override
    public ServerResponse deleteBrand(Long id) {
        brandMapper.deleteBrand(id);
        return ServerResponse.success();
    }

    //修改
    @Override
    public ServerResponse updateBrand(Brand brand) {
        brandMapper.updateBrand(brand);
        return ServerResponse.success();
    }

    //批量删除
    @Override
    public ServerResponse deleteBatch(String ids) {
        brandMapper.deleteBatch(ids);
        return ServerResponse.success();
    }


}
