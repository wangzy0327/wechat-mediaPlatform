package com.wechat.mp.service.impl;

import com.wechat.mp.dao.WxVisitTimeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class WxVisitTimeServiceImplTest {
    @Test
    public void shareAppMessageWxItem() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        String itemId = "neUzquH";
        Integer count = wxVisitTimeMapper.selectCount(openId,itemId);
        System.out.println("count:"+count);
        int column = 0;
        if(count > 0){
            column = wxVisitTimeMapper.updateShareAppMessage(openId,itemId);
        }else{
            column = wxVisitTimeMapper.insertShareAppMessage(openId,itemId);
        }
        System.out.println("column:"+column);
    }

    @Test
    public void shareTimeLineWxItem() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        String itemId = "neUzquH";
        Integer count = wxVisitTimeMapper.selectCount(openId,itemId);
        System.out.println("count:"+count);
        int column = 0;
        if(count > 0){
            column = wxVisitTimeMapper.updateShareTimeLine(openId,itemId);
        }else{
            column = wxVisitTimeMapper.insertShareTimeLine(openId,itemId);
        }
        System.out.println("column:"+column);
    }

    @Autowired
    private WxVisitTimeMapper wxVisitTimeMapper;

    @Test
    public void readWxItemHandler() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        String itemId = "neUzquH";
        Integer count = wxVisitTimeMapper.selectReadTime(openId,itemId);
        System.out.println("count:"+count);
    }


}