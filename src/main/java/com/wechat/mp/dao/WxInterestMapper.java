package com.wechat.mp.dao;

import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.Fans;
import com.wechat.mp.util.wechat.AccountFans;

import java.util.List;

public interface WxInterestMapper {
    Integer insertFansCategory(String openId, Integer cateId);

    List<Integer> getCategoryIdByOpenId(String openId);

    Fans getFansWithInterestByOpenId(String openId);

    Fans getSimpleFansByOpenId(String openId);

    Integer isRelation(String openId, Integer cateId);

    Integer deleteRelation(String openId, Integer cateId);

    Fans findFansInterestInfo(String openId);

    List<Integer> findInterestByOpenId(String openId);
}
