package com.wechat.mp.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信oauth interceptor 处理参数转换；
 * 对于interceptor的url如果有参数，业务中请用此类获取参数；
 */
public class OAuth2RequestParamHelper {
    //准备state参数
    public static String prepareState(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        StringBuilder sb = new StringBuilder("");
        for(String key : map.keySet()){
            if("nsukey".equals(key)){
                continue;
            }
            if(map.get(key) != null && map.get(key).length > 0){
                if(map.get(key)[0] != null){
                    sb.append(key+"="+map.get(key)[0]+"!");//用！间隔
                }
            }
        }
        String str = sb.toString();
        if(StringUtils.isBlank(str)){
            return "";
        }else{
            return str.substring(0, str.length() - 1);
        }
    }
}
