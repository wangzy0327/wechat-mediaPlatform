package com.wechat.mp.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wechat.mp.common.ErrCode;
import com.wechat.mp.util.wechat.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxApiClient {

    static MpAccount mpAccount = null;

    static{
        if(null == mpAccount){
            mpAccount = new MpAccount();
            mpAccount.setAccount(PropertiesUtil.getProperty("wx_account"));
            mpAccount.setToken(PropertiesUtil.getProperty("wx_token"));
            mpAccount.setAppid(PropertiesUtil.getProperty("wx_appid"));
            mpAccount.setAppsecret(PropertiesUtil.getProperty("wx_appsecret"));
        }
    }

    /**
     * 获取公众号
     */
    public static MpAccount getMpAccount(){
        return mpAccount;
    }

    /**
     * 获取accessToken
     * @param
     * @return
     */
    public static String getAccessToken(){
        return getAccessToken(mpAccount);
    }
    public static String getAccessToken(MpAccount mpAccount){
        //获取唯一的accessToken，如果是多账号，请自行处理
        AccessToken token = WxMemoryCacheClient.getSingleAccessToken();
        if(token != null && !token.isExpires()){//不为空，并且没有过期
            return token.getAccessToken();
        }else{
            token = WxApi.getAccessToken(mpAccount.getAppid(),mpAccount.getAppsecret());
            if(token != null){
                if(token.getErrcode() != null){//获取失败
                    System.out.println("## getAccessToken Error = " + token.getErrmsg());
                }else{
                    WxMemoryCacheClient.addAccessToken(mpAccount.getAccount(), token);
                    return token.getAccessToken();
                }
            }
            return null;
        }
    }

    /**
     * 刷新accessToken
     * @param
     * @return
     */
    public static String refreshAccessToken(){
        return refreshAccessToken(mpAccount);
    }
    public static String refreshAccessToken(MpAccount mpAccount){
        //获取唯一的accessToken，如果是多账号，请自行处理
        AccessToken token = WxApi.getAccessToken(mpAccount.getAppid(),mpAccount.getAppsecret());
        if(token != null){
            if(token.getErrcode() != null){//获取失败
                System.out.println("## getAccessToken Error = " + token.getErrmsg());
            }else{
                WxMemoryCacheClient.addAccessToken(mpAccount.getAccount(), token);
                return token.getAccessToken();
            }
        }
        return null;
    }

    /**
     * 获取jsTicket
     * @param
     * @return
     */
    public static String getJSTicket(){
        return getJSTicket(mpAccount);
    }
    public static String getJSTicket(MpAccount mpAccount){
        //获取唯一的JSTicket，如果是多账号，请自行处理
        JSTicket jsTicket = WxMemoryCacheClient.getSingleJSTicket();
        if(jsTicket != null && !jsTicket.isExpires()){//不为空，并且没有过期
            return jsTicket.getTicket();
        }else{
            String token = getAccessToken(mpAccount);
            jsTicket = WxApi.getJSTicket(token);
            if(jsTicket != null){
                if(jsTicket.getErrcode() != null){//获取失败
                    System.out.println("## getJSTicket Error = " + jsTicket.getErrmsg());
                }else{
                    WxMemoryCacheClient.addJSTicket(mpAccount.getAccount(), jsTicket);
                    return jsTicket.getTicket();
                }
            }
            return null;
        }
    }

    /**
     * 获取OAuthAccessToken
     * @param
     * @param code
     * @return
     */
    public static String getOAuthAccessToken(String code){
        return getOAuthAccessToken(mpAccount,code);
    }
    public static String getOAuthAccessToken(MpAccount mpAccount,String code){
        //获取唯一的accessToken，如果是多账号，请自行处理
        OAuthAccessToken token = WxMemoryCacheClient.getSingleOAuthAccessToken();
        if(token != null && !token.isExpires()){//不为空，并且没有过期
            return token.getOauthAccessToken();
        }else{
            token = WxApi.getOAuthAccessToken(mpAccount.getAppid(),mpAccount.getAppsecret(),code);
            if(token != null){
                if(token.getErrcode() != null){//获取失败
                    System.out.println("## getOAuthAccessToken Error = " + token.getErrmsg());
                }else{
                    token.setOpenid(null);//获取OAuthAccessToken的时候设置openid为null；不同用户openid缓存
                    WxMemoryCacheClient.addOAuthAccessToken(mpAccount.getAccount(), token);
                    return token.getOauthAccessToken();
                }
            }
            return null;
        }
    }

    /**
     * 获取openId
     * @param
     * @param code
     * @return
     */
    public static String getOAuthOpenId(String code){
        return getOAuthOpenId(mpAccount,code);
    }
    public static String getOAuthOpenId(MpAccount mpAccount,String code){
        OAuthAccessToken token = WxApi.getOAuthAccessToken(mpAccount.getAppid(),mpAccount.getAppsecret(),code);
        if(token != null){
            if(token.getErrcode() != null){//获取失败
                System.out.println("## getOAuthAccessToken Error = " + token.getErrmsg());
            }else{
                return token.getOpenid();
            }
        }
        return null;
    }

    /**
     * 根据openId获取粉丝信息
     * @param openId
     * @param
     * @return
     */
    public static AccountFans getAccountFans(String openId){
        return getAccountFans(openId, mpAccount);
    }
    public static AccountFans getAccountFans(String openId,MpAccount mpAccount){
        String accessToken = getAccessToken(mpAccount);
        String url = WxApi.getFansInfoUrl(accessToken, openId);
        JSONObject jsonObj = WxApi.httpsRequest(url, "GET", null);
        if (null != jsonObj) {
            if(jsonObj.containsKey("errcode")){
                int errorCode = Integer.valueOf(jsonObj.getString("errcode"));
                System.out.println(String.format("获取用户信息失败 errcode:{} errmsg:{}", errorCode, ErrCode.errMsg(errorCode)));
                return null;
            }else{
                AccountFans fans = new AccountFans();
                fans.setOpenId(jsonObj.getString("openid"));// 用户的标识
                fans.setSubscribeStatus(new Integer(jsonObj.getString("subscribe")));// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                if(jsonObj.containsKey("subscribe_time")){
                    fans.setSubscribeTime(new Date(jsonObj.getLong("subscribe_time")*1000));// 用户关注时间
                }
                if(jsonObj.containsKey("nickname")){// 昵称
                    String nickname = jsonObj.getString("nickname");
                    fans.setNicknameStr(nickname);
                }
                if(jsonObj.containsKey("sex")){// 用户的性别（1是男性，2是女性，0是未知）
                    fans.setGender(Integer.valueOf(jsonObj.getString("sex")));
                }
                if(jsonObj.containsKey("language")){// 用户的语言，简体中文为zh_CN
                    fans.setLanguage(jsonObj.getString("language"));
                }
                if(jsonObj.containsKey("country")){// 用户所在国家
                    fans.setCountry(jsonObj.getString("country"));
                }
                if(jsonObj.containsKey("province")){// 用户所在省份
                    fans.setProvince(jsonObj.getString("province"));
                }
                if(jsonObj.containsKey("city")){// 用户所在城市
                    fans.setCity(jsonObj.getString("city"));
                }
                if(jsonObj.containsKey("headimgurl")){// 用户头像
                    fans.setHeadimgurl(jsonObj.getString("headimgurl"));
                }
                if(jsonObj.containsKey("remark")){
                    fans.setRemark(jsonObj.getString("remark"));
                }
                return fans;
            }
        }
        return null;
    }

    /**
     * 根据accesstoken获取用户列表
     */
    public static List<String> getAccountFansList(){return getAccountFansList(mpAccount);}

    public static List<String> getAccountFansList(MpAccount mpAccount) {
        String accessToken = getAccessToken(mpAccount);
        String fansListUrl = WxApi.getFansList(accessToken,"");
        JSONObject jsonObject = WxApi.httpsRequest(fansListUrl, HttpMethod.GET.getMethodName(), null);
        List<String> list = new ArrayList<>();
        if (jsonObject != null && !jsonObject.containsKey("errcode")) {
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray openIdArr = data.getJSONArray("openid");
            while (openIdArr.size() > 0) {
                String nextOpenId = jsonObject.getString("next_openid");
                for (int i = 0; i < openIdArr.size(); i++) {
                    String openId = (String) openIdArr.get(i);
                    list.add(openId);
                }
                fansListUrl = WxApi.getFansList(accessToken, nextOpenId);
                jsonObject = WxApi.httpsRequest(fansListUrl, HttpMethod.GET.getMethodName(), null);
                data = jsonObject.getJSONObject("data");
                if(data == null){
                    break;
                }
                openIdArr = data.getJSONArray("openid");
            }
            return list;
        } else if (null != jsonObject && jsonObject.containsKey("errcode")) {
            int errorCode = Integer.valueOf(jsonObject.getString("errcode"));
            System.out.println(String.format("获取用户信息失败 errcode:{} errmsg:{}", errorCode, ErrCode.errMsg(errorCode)));
            return null;
        } else {
            return null;
        }
    }

}
