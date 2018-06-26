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
            String openId = wxItemRead.getFans().getOpenId();
            String itemId = wxItemRead.getWxItem().getItemId();
            Integer readTime = wxItemRead.getReadTime();
            Integer readTimes = wxItemRead.getReadTimes();
            Integer shareAppMessage = wxItemRead.getShareAppMessage();
            Integer shareTimeLine = wxItemRead.getShareTimeLine();
            results.add(openId+","+itemId+","+readTime+","+readTimes+","+shareAppMessage+","+shareTimeLine);
        }
        return results;
    }

}
