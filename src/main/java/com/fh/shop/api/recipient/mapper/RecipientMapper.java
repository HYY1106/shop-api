package com.fh.shop.api.recipient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.recipient.po.Recipient;
import org.springframework.stereotype.Repository;

@Repository
//                               继承 extends BaseMapper<Recipient>
public interface RecipientMapper extends BaseMapper<Recipient> {
}
