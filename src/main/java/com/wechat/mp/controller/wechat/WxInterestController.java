package com.wechat.mp.controller.wechat;

import com.alibaba.fastjson.JSONArray;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.service.IWxInterestService;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccountFans;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/Weixin")
public class WxInterestController {

    @Autowired
    private IWxInterestService iWxInterestService;

    @GetMapping(value = "/oauthInterest")
    @ResponseBody
    public ServerResponse oauthGetInterest(String code){
        String openid = "";
        if(code!=null){
            openid = WxApiClient.getOAuthOpenId(WxApiClient.getMpAccount(), code);
            System.out.println("openid:"+openid);
            if(!StringUtils.isBlank(openid)){
                return iWxInterestService.getFansInterest(openid);
            }else{
                return ServerResponse.createByErrorMessage("openid获取失败");
            }
        }else{
            System.out.println("code为空!");
            return ServerResponse.createBySuccessMessage("code为空!");
        }
    }

    @GetMapping(value = "/accountFanInterest")
    @ResponseBody
    public ServerResponse GetAccountInterest(String openId){
        if(!StringUtils.isBlank(openId)){
            return iWxInterestService.getFansInterest(openId);
        }else{
            return ServerResponse.createByErrorMessage("openid错误");
        }
    }

    @GetMapping(value="/category.json")
    @ResponseBody
    public ServerResponse GetInterestList(){
        return iWxInterestService.getInterestList();
    }

    @GetMapping(value = "/oauthFansInterestInfo")
    @ResponseBody
    public ServerResponse oauthFansInterestInfo(String code){
        String openid = "";
        if(code!=null){
            openid = WxApiClient.getOAuthOpenId(WxApiClient.getMpAccount(), code);
            System.out.println("openid:"+openid);
            if(!StringUtils.isBlank(openid)){
                return iWxInterestService.getFansInterestInfo(openid);
            }else{
                return ServerResponse.createByErrorMessage("openid获取失败");
            }
        }else{
            System.out.println("code为空!");
            return ServerResponse.createBySuccessMessage("code为空!");
        }
    }

    @GetMapping(value = "/FansInterestInfo")
    @ResponseBody
    public ServerResponse GetInterestInfo(String openId){
        if(!StringUtils.isBlank(openId)){
            return iWxInterestService.getFansInterestInfo(openId);
        }else{
            return ServerResponse.createByErrorMessage("openid错误");
        }
    }

    @PostMapping(value = "/editInterest")
    @ResponseBody
    public ServerResponse EditInterest(@RequestParam(value = "openId") String openId,@RequestParam(value = "interests") List<Integer> interests ){
//        @RequestParam(value = "interests") Integer[] interests
//        List<Integer> interestList = Arrays.asList(interests);


        /**
         $.ajax({
         type: 'POST',//提交方式 post 或者get
         url: "/wechat-tools/Weixin/editInterest",//提交到那里 后他的服务
         traditional: true,
         data:"openId="+wxopenid+"&interests="+checked,
         success:function(result){
         if(result.code == 0){
         window.location.href = "interest_preview.html";
         }
         },
         error:function(){
         $.alert("兴趣录入异常！", "警告！");
         }
         });
         */
        return iWxInterestService.editInterest(openId,interests);
    }

}
