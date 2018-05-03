package com.wechat.mp.test;

import com.wechat.mp.util.wechat.OAuthScope;
import com.wechat.mp.util.PropertiesUtil;
import com.wechat.mp.util.WxApi;

public class RedirectUrl {
    public static void main(String[] args) {
//        WxMpService wxMpService = new WxMpServiceImpl();
        String redirectUrl = "http://wangzy.tunnel.qydev.com/wechat-tools/page/oauth/self_detail.html";
        System.out.println(redirectUrl);
        String url = WxApi.getOAuthCodeUrl(PropertiesUtil.getProperty("wx_appid"), redirectUrl, OAuthScope.Userinfo.toString(), null);
        System.out.println(url);
    }
}
