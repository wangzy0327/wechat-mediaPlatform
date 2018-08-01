package com.wechat.mp.service.impl;

import com.wechat.mp.dao.WxVisitTimeMapper;
import com.wechat.mp.pojo.WxItemRead;
import com.wechat.mp.service.IDataOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component("iDataOutputService")
public class DataOutputServiceImpl implements IDataOutputService {

    @Autowired
    private WxVisitTimeMapper wxVisitTimeMapper;

    public List<String> getFansRead(){
        List<String> results = new LinkedList<>();
        List<WxItemRead> wxItemReads = wxVisitTimeMapper.selectAll();
        System.out.println("get Mapper.....");
        for(int i = 0;i<wxItemReads.size();i++){
            WxItemRead wxItemRead = wxItemReads.get(i);
            Integer fansId = wxItemRead.getFans().getId();
            Integer wechatItemId = wxItemRead.getWxItem().getId();
            String openId = wxItemRead.getFans().getOpenId();
            String itemId = wxItemRead.getWxItem().getItemId();
            Integer readTime = wxItemRead.getReadTime();
            Integer readTimes = wxItemRead.getReadTimes();
            Integer shareAppMessage = wxItemRead.getShareAppMessage();
            Integer shareTimeLine = wxItemRead.getShareTimeLine();
            Integer score = null;
            if(shareTimeLine > 0){
                score = 5;
            }else if(shareAppMessage > 0){
                score = 4;
            }else if(readTimes>=2 && readTime >=3){
                score = 3;
            }else if(readTime>=2 && readTime <3){
                score = 2;
            }else{
                score = 1;
            }
//            results.add(openId+","+itemId+","+readTime+","+readTimes+","+shareAppMessage+","+shareTimeLine);
            results.add(fansId+","+wechatItemId+","+score);
        }
        return results;
    }

}