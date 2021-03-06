package com.wechat.mp.service;

import com.wechat.mp.common.ServerResponse;

public interface IWxVisitTimeService {

    ServerResponse readWxItemHandler(String openId,String itemId,Integer spendTime);

    ServerResponse shareAppMessageWxItem(String openId, String itemId);

    ServerResponse shareTimeLineWxItem(String openId, String itemId);
}
