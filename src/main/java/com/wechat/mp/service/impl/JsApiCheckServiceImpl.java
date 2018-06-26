package com.wechat.mp.service.impl;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.service.IJsApiCheckService;
import com.wechat.mp.util.ByteHex;
import com.wechat.mp.util.PropertiesUtil;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.JSConfig;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service("iJsApiCheckService")
public class JsApiCheckServiceImpl implements IJsApiCheckService {

    @Override
    public ServerResponse getJsApiTicket(String requestUrl) {
        String appId = PropertiesUtil.getProperty("wx_appid");
        String signature = "";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
        String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
        System.out.println("nonceStr:"+nonceStr);
        System.out.println("timestamp:"+timestamp);
        String jsApiTicket = WxApiClient.getJSTicket();
        System.out.println("jsApiTicket:"+jsApiTicket);
        String sign = "jsapi_ticket=" + jsApiTicket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + requestUrl;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));
            signature = ByteHex.byteToHex(crypt.digest());
            System.out.println(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ServerResponse.createBySuccess(new JSConfig(appId,timestamp,nonceStr,signature));
    }
}
