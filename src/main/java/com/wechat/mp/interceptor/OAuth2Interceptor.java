package com.wechat.mp.interceptor;

import com.wechat.mp.common.wechat.MpAccount;
import com.wechat.mp.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class OAuth2Interceptor extends HandlerInterceptorAdapter {

    /**
     * 开发者自行处理拦截逻辑，
     * 方便起见，此处只处理includes
     */
    private String[] excludes;//不需要拦截的
    private String[] includes;//

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        System.out.println(uri);
        boolean oauthFlag = false;//为方便展示的参数，开发者自行处理
        for (String s : includes) {
            if (uri.contains(s)) {//如果包含，就拦截
                oauthFlag = true;
                break;
            }
        }
        if (!oauthFlag) {//如果不需要oauth认证
            return true;
        }

        String sessionid = request.getSession().getId();

        //先从缓存中获取openid
        String openid = WxMemoryCacheClient.getOpenid(sessionid);
        if(StringUtils.isEmpty(openid)){//没有，通过微信页面授权获取
            String code = request.getParameter("code");
            /**
             * 如果request中包括code，则是微信回调
             */
            if(StringUtils.isNotBlank(code)){
                try {
                    System.out.println("微信回调code："+code);
                    openid = WxApiClient.getOAuthOpenId(WxApiClient.getMpAccount(), code);
                    System.out.println("openid:"+openid);
                    if(!StringUtils.isBlank(openid)){

                        //缓存openid，在具体业务代码中直接从缓存中取即可
                        WxMemoryCacheClient.setOpenid(sessionid, openid);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{//oauth获取code
                MpAccount mpAccount = WxApiClient.getMpAccount();
                String redirectUrl = HttpUtil.getRequestFullUriNoContextPath(request);//请求code的回调url
                String state = OAuth2RequestParamHelper.prepareState(request);

                /**
                 * 请求微信OAuth认证接口
                 * 微信服务器会回调：redirectUrl （即当前拦截的url）
                 */
                String url = WxApi.getOAuthCodeUrl(mpAccount.getAppid(), redirectUrl, OAuthScope.Userinfo.toString(), state);
                System.out.println("重定向URL："+url);
                HttpUtil.redirectHttpUrl(request, response, url);
                return false;
            }
        }else{
            System.out.println("#### WxOAuth2Interceptor Session : openid = " + openid);
            return true;
        }
        HttpUtil.redirectHttpUrl(request, response, "error404.html");
        return false;
    }


    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public boolean checkList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
