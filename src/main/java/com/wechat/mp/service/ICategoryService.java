package com.wechat.mp.service;

import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryService {

    ServerResponse<List<Category>> findCategory();

    List<Category> findCategoryByParam(Map<String,String> param);

    Integer findCategoryCount();

    Integer findCategoryCountByParam(Map<String,String> param);

    ResponseCode saveNewCategory(Category category);

    ServerResponse findCategoryById(Integer id);

    ResponseCode editCategory(Category category);

    ServerResponse delCategory(Integer id);

}
