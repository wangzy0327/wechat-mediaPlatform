package com.wechat.mp.dao;

import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.Tag;
import com.wechat.mp.pojo.WxItem;

import java.util.List;
import java.util.Map;

public interface WxItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxItem record);

    int insertSelective(WxItem record);

    WxItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxItem record);

    int updateByPrimaryKey(WxItem record);

    List<WxItem> findWxItemByParam(Map<String,String> param);

    Integer findWxItemCount();

    Integer findWxItemCountByParam(Map<String,String> param);

    Integer insertWxItemTags(WxItem wxItem, Tag tag);

    Integer insertWxItemCategory(WxItem wxItem, Category category);

    Integer findByIndex(String itemId);
}