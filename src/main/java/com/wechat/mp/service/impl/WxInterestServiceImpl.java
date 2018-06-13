package com.wechat.mp.service.impl;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.CategoryMapper;
import com.wechat.mp.dao.FansMapper;
import com.wechat.mp.dao.WxInterestMapper;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.Fans;
import com.wechat.mp.service.IWxInterestService;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccountFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("iWxInterestService")
public class WxInterestServiceImpl implements IWxInterestService {

    @Autowired
    private WxInterestMapper wxInterestMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获取展示粉丝兴趣列表
     * @param openId
     * @return
     */
    @Override
    public ServerResponse getFansInterest(String openId) {
        Fans fans = wxInterestMapper.getFansWithInterestByOpenId(openId);
        if(fans == null){
            fans = wxInterestMapper.getSimpleFansByOpenId(openId);
            System.out.println(fans.getAccountFans());
            return ServerResponse.createBySuccess(fans);
        }
        System.out.println(fans);
        return ServerResponse.createBySuccess(fans);
    }

    @Override
    public ServerResponse getInterestList(){
        List<Category> categories = categoryMapper.findInterestList();
        if(categories!=null){
            return ServerResponse.createBySuccess(categories);
        }
        return ServerResponse.createByErrorMessage("兴趣列表获取失败");
    }

    /**
     * 获取编辑粉丝已关注兴趣列表
     * @return
     */
    @Override
    public ServerResponse getFansInterestInfo(String openId) {
        Fans fans = wxInterestMapper.findFansInterestInfo(openId);
        return ServerResponse.createBySuccess(fans);
    }

    @Override
    public ServerResponse editInterest(String openId, List<Integer> interests) {
        List<Integer> oldInterests = wxInterestMapper.findInterestByOpenId(openId);
        for(int i = 0;i<interests.size();i++){
            if(!oldInterests.contains(interests.get(i))){
                wxInterestMapper.insertFansCategory(openId,interests.get(i));
            }
        }
        for(int i = 0;i<oldInterests.size();i++){
            if(!interests.contains(oldInterests.get(i))){
                wxInterestMapper.deleteRelation(openId,oldInterests.get(i));
            }
        }
        return ServerResponse.createBySuccess();
    }


}
