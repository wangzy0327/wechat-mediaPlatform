package com.wechat.mp.dao;

import com.wechat.mp.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> findCategoryList();

    Category selectByName(String name);

    List<Category> findCategoryByParam(Map<String,String> param);

    Integer findCategoryCount();

    Integer findCategoryCountByParam(Map<String,String> param);

    Integer findCategoryCountByName(String name);

    Category findCategoryByName(String name);

    Integer findCategoryItemCount(Integer cate_id);

    List<Category> findInterestList();

}