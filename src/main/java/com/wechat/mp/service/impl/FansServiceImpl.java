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
//        AccountFans fan = fansMapper.selectByPrimaryKey("ohIIkv5dPrjtGbbTAwgeV4isWXeg");
//        System.out.println(fan);
//        System.out.println(fan.getNicknameStr());

//        try {
//            AccountFans fan = new AccountFans();
//            fan.setOpenId("ohIIkv5dPrjtGbbTAwgeV4isWXeg");
//            fan.setSubscribeStatus(1);
//            fan.setSubscribeTime(DateUtil.COMMON_FULL.getTextDate("2018-05-18 11:51:46"));
//            fan.setNicknameStr("击剑___婧\uD83E\uDD3A\uD83E\uDD3A\uD83E\uDD3A");
//            System.out.println(fan.getNicknameStr());
//            fan.setGender(2);
//            fan.setCountry("中国");
//            fan.setProvince("山西");
//            fan.setCity("太原");
//            fan.setHeadimgurl("http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM6nm4TwcmjyKwamfXgJAic5kIWueX7OFN3fzhOP82ibUA8Nt4BDD1GkHDHUVrtS96a1ibX746EQCAtnXvnrwzSSDwx1aWibEjSZDs0/132");
//            fansMapper.insertSelective(fan);
//
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
