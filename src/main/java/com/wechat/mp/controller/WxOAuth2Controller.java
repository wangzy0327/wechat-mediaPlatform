package com.wechat.mp.controller;


import com.wechat.mp.util.wechat.AccountFans;
import com.wechat.mp.util.wechat.MpAccount;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.WxMemoryCacheClient;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Weixin")
public class WxOAuth2Controller {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping(value = "/oauth")
    @ResponseBody
    public ModelAndView self_detail(HttpServletRequest request) {
        MpAccount mpAccount = WxApiClient.getMpAccount();//获取缓存中的唯一账号
        if (mpAccount != null) {
            ModelAndView mv = new ModelAndView("oauth/self_detail");
            /**
             * 在拦截器WxOAuth2Interceptor中缓存了  openid
             * 这里直接取
             */
            String openId = WxMemoryCacheClient.getOpenid(request.getSession().getId());
            int subscribe = 0;//0是未关注  1是关注
            if (null != openId) {
                AccountFans fans = WxApiClient.getAccountFans(openId);
                if (fans.getSubscribeStatus().intValue() == 0) {
                    System.out.println("用户未关注....");
                } else {
                    subscribe = 1;
                    fans.setNicknameStr(new String(fans.getNickname()));
                }
                mv.addObject("curUser", fans);
                mv.addObject("subscribe", subscribe);
                System.out.println(fans);
            }
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("errer404");
            mv.addObject("message", "OAuth获取openId失败");
            return mv;
//            return "";
        }
    }

}
