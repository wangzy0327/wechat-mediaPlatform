package com.wechat.mp.dao;

import com.wechat.mp.pojo.TagItem;

public interface TagItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagItem record);

    int insertSelective(TagItem record);

    TagItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagItem record);

    int updateByPrimaryKey(TagItem record);
}