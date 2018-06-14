package com.wechat.mp.dao;

import com.wechat.mp.pojo.WxItemRead;

import java.util.List;

public interface WxVisitTimeMapper {

    Integer insert(String openId,String wxItemId,Integer spendTime);

    Integer selectReadTime(String openId,String wxItemId);

    Integer updateReadTime(String openId,String wxItemId,Integer spendTime);

    Integer updateReadTimes(String openId,String wxItemId,Integer spendTime);

    List<WxItemRead> selectAll();

}
