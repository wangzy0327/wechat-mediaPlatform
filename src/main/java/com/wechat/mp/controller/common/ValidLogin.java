package com.wechat.mp.controller.common;

import com.wechat.mp.common.Const;
import com.wechat.mp.pojo.User;

import javax.servlet.http.HttpSession;

public class ValidLogin {
    public static boolean isLogin(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return false;
        }else{
            return true;
        }
    }
}
