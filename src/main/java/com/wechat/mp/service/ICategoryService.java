package com.wechat.mp.service;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.Category;

import java.util.List;

public interface ICategoryService {

    public ServerResponse<List<Category>> findCategory();

}
