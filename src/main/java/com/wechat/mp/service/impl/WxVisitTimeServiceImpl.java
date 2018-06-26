package com.wechat.mp.service.impl;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.WxVisitTimeMapper;
import com.wechat.mp.pojo.WxItemRead;
import com.wechat.mp.service.IWxVisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iWxVisitTimeService")
public class WxVisitTimeServiceImpl implements IWxVisitTimeService {

    @Autowired
    private WxVisitTimeMapper wxVisitTimeMapper;

    @Override
    public ServerResponse readWxItemHandler(String openId, String itemId, Integer spendTime) {
        Integer readTime = wxVisitTimeMapper.selectReadTime(openId,itemId);
        int column = 0;
        if(readTime != null){
            if(readTime>spendTime){
                column = wxVisitTimeMapper.updateReadTimes(openId,itemId,spendTime);
            }else{
                column = wxVisitTimeMapper.updateReadTime(openId,itemId,spendTime);
            }
        }else{
            column = wxVisitTimeMapper.insertReadTime(openId,itemId,spendTime);
        }
        if(column > 0)
            return ServerResponse.createBySuccess();
        else{
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse shareAppMessageWxItem(String openId, String itemId){
        Integer count = wxVisitTimeMapper.selectCount(openId,itemId);
        int column = 0;
        if(count > 0){
            column = wxVisitTimeMapper.updateShareAppMessage(openId,itemId);
        }else{
            column = wxVisitTimeMapper.insertShareAppMessage(openId,itemId);
        }
        if(column > 0)
            return ServerResponse.createBySuccess();
        else{
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse shareTimeLineWxItem(String openId, String itemId){
        Integer count = wxVisitTimeMapper.selectCount(openId,itemId);
        int column = 0;
        if(count > 0){
            column = wxVisitTimeMapper.updateShareTimeLine(openId,itemId);
        }else{
            column = wxVisitTimeMapper.insertShareTimeLine(openId,itemId);
        }
        if(column > 0)
            return ServerResponse.createBySuccess();
        else{
            return ServerResponse.createByError();
        }
    }


}
