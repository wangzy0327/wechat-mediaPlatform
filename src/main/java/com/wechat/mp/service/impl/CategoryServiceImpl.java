package com.wechat.mp.service.impl;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.CategoryMapper;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse<List<Category>> findCategory() {
        List<Category> categories = categoryMapper.findCategoryList();
        if(categories!=null){
            return ServerResponse.createBySuccess(categories);
        }else{
            return ServerResponse.createByErrorMessage("暂时无类别信息");
        }
    }
}
