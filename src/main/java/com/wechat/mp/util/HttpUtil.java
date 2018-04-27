package com.wechat.mp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtil {
    public static String getRequestFullUriNoContextPath(HttpServletRequest request){
        String port = "";
        if(request.getServerPort() != 80){
            port = ":" + request.getServerPort();
        }
        return request.getScheme() + "://" + request.getServerName() + port + request.getContextPath() + request.getServletPath();
    }

    public static void redirectHttpUrl(HttpServletRequest request, HttpServletResponse response, String url){
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
