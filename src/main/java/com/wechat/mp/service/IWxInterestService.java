package com.wechat.mp.service;

import com.wechat.mp.common.ServerResponse;

import java.util.List;

public interface IWxInterestService {

    ServerResponse getFansInterest(String openId);

    ServerResponse getInterestList();

    ServerResponse getFansInterestInfo(String openId);

    ServerResponse editInterest(String openId, List<Integer> interests);

}
