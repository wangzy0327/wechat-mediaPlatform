package com.wechat.mp.service;

import com.wechat.mp.common.ServerResponse;

public interface IWxVisitTimeService {

    ServerResponse readWxItemHandler(String openId,String itemId,Integer spendTime);

}
