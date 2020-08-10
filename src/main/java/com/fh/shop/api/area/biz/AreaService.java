package com.fh.shop.api.area.biz;

import com.fh.shop.api.common.ServerResponse;

public interface AreaService {

    ServerResponse findChilds(Long id);

    //ServerResponse findAreaList(Long id);

    //ServerResponse findAreaTree();
}
