package com.wechat.mp.service.impl;

import com.wechat.mp.dao.WxVisitTimeMapper;
import com.wechat.mp.pojo.WxItemRead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class DataOutputServiceImplTest {

    @Autowired
    private WxVisitTimeMapper wxVisitTimeMapper;

    @Test
    public void getFansRead() throws Exception {
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
        System.out.println(Arrays.deepToString(results.toArray()));
    }

}