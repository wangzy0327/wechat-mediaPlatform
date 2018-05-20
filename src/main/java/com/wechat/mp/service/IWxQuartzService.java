package com.wechat.mp.service;

import com.wechat.mp.util.wechat.AccountFans;

import java.util.List;

public interface IWxQuartzService {

    void updateFans(List<AccountFans> fansList);

    List<AccountFans> getFansInfo(List<String> list);

}
