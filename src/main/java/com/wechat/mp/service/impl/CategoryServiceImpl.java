package com.wechat.mp.service.impl;

import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.CategoryMapper;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据DataTables中的参数进行查询类别信息
     * @param param
     * @return
     */
    public List<Category> findCategoryByParam(Map<String,String> param){
        return categoryMapper.findCategoryByParam(param);
    }

    /**
     * 获取类别的总数量
     * @return
     */
    public Integer findCategoryCount() {
        return categoryMapper.findCategoryCount();
    }

    /**
     * 根据查询条件获取类别的数量
     * @param param
     * @return
     */
    public Integer findCategoryCountByParam(Map<String,String> param){
        return categoryMapper.findCategoryCountByParam(param);
    }

    /**
     * 添加新的类别
     * @param category
     * @return
     */
    public ResponseCode saveNewCategory(Category category){
        if(categoryMapper.findCategoryCountByName(category.getName()).intValue()>0){
            return ResponseCode.DUPLICATE;
        }else{
            if(categoryMapper.insert(category)>0){
                return ResponseCode.SUCCESS;
            }else {
                return ResponseCode.ERROR;
            }
        }
    }

    public ServerResponse findCategoryById(Integer id){
        Category category = categoryMapper.selectByPrimaryKey(id);
        System.out.println(category);
        if(category == null){
            return ServerResponse.createByErrorMessage("图文信息获取已删除或不存在！");
        }else {
            return ServerResponse.createBySuccess(category);
        }
    }

    public ResponseCode editCategory(Category category){
        String name = category.getName();
        Category findCategory = categoryMapper.findCategoryByName(category.getName());
        if(findCategory == null || findCategory.getId().equals(category.getId())){
            // TODO
            if(categoryMapper.updateByPrimaryKey(category)>0)
                return ResponseCode.SUCCESS;
            else{
                return ResponseCode.ERROR;
            }
        }else{
            return ResponseCode.DUPLICATE;
        }
    }

    public ServerResponse delCategory(Integer id) {
        int count = categoryMapper.findCategoryItemCount(id);
        if (count > 0) {
            return ServerResponse.createByErrorMessage("该分类包含有相关图文消息，不可删除！请先处理有关图文消息");
        } else {
            categoryMapper.deleteByPrimaryKey(id);
            return ServerResponse.createBySuccess();
        }
    }

}
