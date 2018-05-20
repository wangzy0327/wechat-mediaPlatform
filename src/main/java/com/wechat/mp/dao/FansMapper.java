package com.wechat.mp.dao;

import com.wechat.mp.util.wechat.AccountFans;

import java.util.List;

public interface FansMapper {
    int deleteByPrimaryKey(String openId);

    int insert(AccountFans record);

    int insertSelective(AccountFans record);

    AccountFans selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(AccountFans record);

    int updateByPrimaryKey(AccountFans record);

    List<String> findProvince();

    int findCountByPrimaryKey(String openId);

}