package com.fh.shop.api.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.area.po.Area;
import org.springframework.stereotype.Repository;

@Repository
//继承xtends BaseMapper<Area>
public interface AreaMapper extends BaseMapper<Area> {
}
