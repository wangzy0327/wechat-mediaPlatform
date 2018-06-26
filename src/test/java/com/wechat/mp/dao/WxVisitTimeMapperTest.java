package com.wechat.mp.dao;

import com.wechat.mp.pojo.WxItemRead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class WxVisitTimeMapperTest {

    @Autowired
    private WxVisitTimeMapper wxVisitTimeMapper;

    @Test
    public void insert() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        String itemId = "neUzquH";
        Integer spendTime = 5;
        int column = wxVisitTimeMapper.insertReadTime(openId,itemId,spendTime);
        System.out.println(column);
    }

    @Test
    public void updateReadTime() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        String itemId = "FvmIZ3i";
        Integer spendTime = 5;
        int updateColumn = wxVisitTimeMapper.updateReadTime(openId,itemId,spendTime);
        System.out.println(updateColumn);
    }

    @Test
    public void updateReadTimes() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        String itemId = "FvmIZ3i";
        Integer spendTime = 4;
        int updateColumn = wxVisitTimeMapper.updateReadTimes(openId,itemId,spendTime);
        System.out.println(updateColumn);
    }

    @Test
    public void selectReadTime() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
//        String itemId = "FvmIZ3i";
        String itemId = "36RZriA";
        Integer readTime = wxVisitTimeMapper.selectReadTime(openId,itemId);
        System.out.println(readTime);
    }

    @Test
    public void selectAll() throws Exception {
        List<WxItemRead> list = wxVisitTimeMapper.selectAll();
        System.out.println(Arrays.deepToString(list.toArray()));
    }

}