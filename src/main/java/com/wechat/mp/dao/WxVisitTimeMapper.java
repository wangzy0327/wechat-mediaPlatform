package com.wechat.mp.dao;

import com.wechat.mp.pojo.WxItemRead;

import java.util.List;

public interface WxVisitTimeMapper {

    Integer insertReadTime(String openId,String wxItemId,Integer spendTime);

    Integer selectReadTime(String openId,String wxItemId);

    Integer updateReadTime(String openId,String wxItemId,Integer spendTime);

    Integer updateReadTimes(String openId,String wxItemId,Integer spendTime);

    List<WxItemRead> selectAll();

    Integer selectCount(String openId,String wxItemId);

    Integer insertShareAppMessage(String openId,String wxItemId);

    Integer updateShareAppMessage(String openId,String wxItemId);

    Integer insertShareTimeLine(String openId,String wxItemId);

    Integer updateShareTimeLine(String openId,String wxItemId);


}
