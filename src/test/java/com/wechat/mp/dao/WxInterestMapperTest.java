package com.wechat.mp.dao;

import com.wechat.mp.pojo.Fans;
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
public class WxInterestMapperTest {

    @Autowired
    private WxInterestMapper wxInterestMapper;

    @Test
    public void insertFansCategory() throws Exception {
    }

    @Test
    public void getCategoryIdByOpenId() throws Exception {
    }

    @Test
    public void getFansByOpenId() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        Fans fans = wxInterestMapper.getFansWithInterestByOpenId(openId);
        System.out.println(fans);
    }

    @Test
    public void getSimpleFansByOpenId(){
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        Fans fans = wxInterestMapper.getSimpleFansByOpenId(openId);
        System.out.println(fans);
    }

    @Test
    public void findFansInterestInfo() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        Fans fans = wxInterestMapper.findFansInterestInfo(openId);
        System.out.println(fans);
    }

    @Test
    public void insertFansCategory1() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        int cateId = 22;
        int column = wxInterestMapper.insertFansCategory(openId,cateId);
        System.out.println(column);
    }

    @Test
    public void deleteRelation1() throws Exception {
        String openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
        int cateId = 22;
        int column = wxInterestMapper.deleteRelation(openId,cateId);
        System.out.println(column);
    }

    @Test
    public void isRelation() throws Exception {
    }

    @Test
    public void deleteRelation() throws Exception {
    }

}