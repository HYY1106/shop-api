package com.fh.shop.api.area.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.mapper.AreaMapper;
import com.fh.shop.api.area.po.Area;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
                        //而用 @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注
@Service("areaService")//用于标注业务层组件,这个注解是写在类上面的，标注将这个类交给Spring容器管理，spring容器要为他创建对象
public class AreaServiceImpl implements AreaService {

   //私有化mapper层(@Resource的作用相当于@Autowired,只不过@Autowired按byType自动注入,而@Resource默认按 byName自动注入罢了。)
    @Autowired
    private AreaMapper areaMapper;



    @Override
    public ServerResponse findChilds(Long id) {
        QueryWrapper<Area> areaQueryWrapper = new QueryWrapper<>();
        areaQueryWrapper.eq("pid",id);
        List<Area> areas =  areaMapper.selectList(areaQueryWrapper);
        return ServerResponse.success(areas);
    }

     /* @Override
      public ServerResponse findAreaList(Long id) {
        return null;
   }

     @Override
      public ServerResponse findAreaTree() {
         return null;
      }*/
 }
