package com.wechat.mp.controller;


import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccountFans;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Weixin")
public class WxOAuth2Controller {

    @GetMapping(value = "/oauth")
    @ResponseBody
    public ServerResponse oauthAuthentication(HttpServletRequest request,String code) {
        String openid = "";
        if(code!=null){
            openid = WxApiClient.getOAuthOpenId(WxApiClient.getMpAccount(), code);
            System.out.println("openid:"+openid);
            if(!StringUtils.isBlank(openid)){
                //缓存openid，在具体业务代码中直接从缓存中取即可
                AccountFans fans = WxApiClient.getAccountFans(openid);
                System.out.println(fans);

                return ServerResponse.createBySuccess(fans);
            }else{
                return ServerResponse.createByErrorMessage("openid获取失败");
            }
        }else{
            System.out.println("code为空!");
            return ServerResponse.createBySuccessMessage("code为空!");
        }
    }

    @GetMapping(value = "/accountFanInfo")
    @ResponseBody
    public ServerResponse selfAccountFanInfo(HttpServletRequest request,String openId) {
        if(!StringUtils.isBlank(openId)){
            //缓存openid，在具体业务代码中直接从缓存中取即可
            AccountFans fans = WxApiClient.getAccountFans(openId);
            System.out.println(fans);

            return ServerResponse.createBySuccess(fans);
        }else{
            return ServerResponse.createByErrorMessage("openid获取失败");
        }
    }


}
