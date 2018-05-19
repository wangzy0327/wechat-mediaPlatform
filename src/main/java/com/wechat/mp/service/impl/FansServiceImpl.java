package com.wechat.mp.service.impl;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.FansMapper;
import com.wechat.mp.service.IFansService;
import com.wechat.mp.util.HexByteUtil;
import com.wechat.mp.util.StrHexUtil;
import com.wechat.mp.util.wechat.AccountFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("iFansService")
public class FansServiceImpl implements IFansService {

    @Autowired
    private FansMapper fansMapper;

    public ServerResponse loadMap(){
        Map<String,Integer> map = new HashMap();
        List<String> provinces = fansMapper.findProvince();

        //插入一个AccountFans类型


//        try {
//            AccountFans fan = new AccountFans();
//            fan.setOpenId("ohIIkv0EWHRMIFNgCn9iM4obFktI");
//            fan.setSubscribeStatus(1);
//            fan.setNicknameStr("笑傲江湖");
//            fan.setNickname("笑傲江湖".getBytes("UTF-8"));
//            fan.setSubscribeTime(new Date());
//            fan.setGender(1);
//            fan.setCountry("中国");
//            fan.setProvince("山西");
//            fan.setCity("临汾");
//            fan.setHeadimgurl("http://thirdwx.qlogo.cn/mmopen/1LlgQzJVOyCoetYw1a2MXwTX785vnABwc4reUOibB2oBcrKs0QxibsWHRmeL3PlbluibOuicaXP7ukFVBWLcAehqibTJRmYQibeFl4/132");
//            fansMapper.insertSelective(fan);
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


//        System.out.println("insert success!");

        int max = 0;
        for(int i = 0;i < provinces.size();i++){
            if(!map.containsKey(provinces.get(i))){
                if(max < 1){
                    max = 1;
                }
                map.put(provinces.get(i),1);
            }else{
                map.put(provinces.get(i),map.get(provinces.get(i))+1);
                if(max < map.get(provinces.get(i))){
                    max = map.get(provinces.get(i));
                }
            }
        }
        System.out.println("max:"+max);
        map.put("max",max);
        return ServerResponse.createBySuccess(map);
    }


}
