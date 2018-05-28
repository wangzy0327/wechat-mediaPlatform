package com.wechat.mp.service.impl;

import com.wechat.mp.dao.FansMapper;
import com.wechat.mp.service.IWxQuartzService;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccountFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("iWxQuartzService")
public class WxQuartzServiceImpl implements IWxQuartzService {

    @Autowired
    private FansMapper fansMapper;

    public void updateFans(List<AccountFans> fansList){
        for(int i = 0;i<fansList.size();i++){
            if(fansMapper.findCountByPrimaryKey(fansList.get(i).getOpenId())>0){
                System.out.println("update(更新):"+fansList.get(i).getNicknameStr());
                fansMapper.updateByPrimaryKeySelective(fansList.get(i));
            }else{
                System.out.println("insert(插入):"+fansList.get(i).getNicknameStr());
                fansMapper.insertSelective(fansList.get(i));
            }
        }
    }


    public List<AccountFans> getFansInfo(List<String> list){
        List<AccountFans> fansList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            String openId = list.get(i);
            System.out.println("第"+(i+1)+"个粉丝: ");
            AccountFans fans = WxApiClient.getAccountFans(openId);
            fansList.add(fans);
//            System.out.println(fans.getSubscribeTime());
            System.out.println(fans);
            System.out.println(fans.getSubscribeTime());
        }
        return fansList;
    }

}
