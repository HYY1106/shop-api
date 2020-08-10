package com.fh.shop.api.recipient.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.recipient.mapper.RecipientMapper;
import com.fh.shop.api.recipient.po.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recipientService")
public class RecipientServiceImpl implements RecipientService{


    @Autowired
    private RecipientMapper recipientMapper;




    //查询 作废
    /*@Override
    public ServerResponse findList(Long memberId) {
        QueryWrapper<Recipient> recipientQueryWrapper = new QueryWrapper<>();
                                 //数据库对应        上面参数对应
        recipientQueryWrapper.eq("memberId",memberId);
        List<Recipient> recipients = recipientMapper.selectList(recipientQueryWrapper);
        return ServerResponse.success(recipients);
    }*/
    @Override
    public List<Recipient> findList(Long memberId) {
        QueryWrapper<Recipient> recipientQueryWrapper = new QueryWrapper<>();
                                        //数据库对应        上面参数对应
        recipientQueryWrapper.eq("memberId",memberId);
        List<Recipient> recipientList = recipientMapper.selectList(recipientQueryWrapper);
        return recipientList;
    }
}
