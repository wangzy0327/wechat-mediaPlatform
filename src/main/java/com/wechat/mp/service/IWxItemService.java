package com.wechat.mp.service;

import com.wechat.mp.pojo.WxItem;

import java.util.List;
import java.util.Map;

public interface IWxItemService {

    List<WxItem> findWxItemByParam(Map<String, String> param);

    Integer findWxItemCount();

    Integer findWxItemCountByParam(Map<String, String> param);
}
