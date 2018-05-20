package com.wechat.mp.service.impl;

import com.wechat.mp.common.DateUtil;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.FansMapper;
import com.wechat.mp.service.IFansService;
import com.wechat.mp.util.HexByteUtil;
import com.wechat.mp.util.StrHexUtil;
import com.wechat.mp.util.wechat.AccountFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
//            fan.setOpenId("ohIIkv91Vv6UGB9C_bty0NT-EgJc");
//            fan.setSubscribeStatus(1);
//            fan.setSubscribeTime(DateUtil.COMMON_FULL.getTextDate("2018-05-17 20:12:57"));
//            fan.setNicknameStr("伊邪");
//            fan.setNickname("伊邪".getBytes("UTF-8"));
//            fan.setSubscribeTime(new Date());
//            fan.setGender(1);
//            fan.setCountry("挪威");
//            fan.setProvince("");
//            fan.setCity("");
//            fan.setHeadimgurl("http://thirdwx.qlogo.cn/mmopen/BicpoNMJYncY71daWYia9WXbM6e60KXeh9ZXbzicNBUjvVqJlFEAnS1c5c5nYd9IZBQdtbLeCVUZf1LkmUccgYJ1ytaCDhdVzNw/132");
//            fansMapper.insertSelective(fan);
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
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
