package com.fh.shop.api.recipient.biz;

import com.fh.shop.api.recipient.po.Recipient;

import java.util.List;

public interface RecipientService {

     //查询 作废
    //ServerResponse findList(Long memberId);
    public List<Recipient> findList(Long memberId);
}
