package com.wechat.mp.controller.backend;

import com.wechat.mp.common.Const;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.User;
import com.wechat.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user/")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "login")
    @ResponseBody
    public ServerResponse<User> login(String username,String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        ModelAndView mv = new ModelAndView();
        if (response.isSuccess()) {
            User u = response.getData();
            if (u.getRole() == Const.Role.ROLE_ADMIN) {
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER, u);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员,无法登录");
            }
        }
        return response;
    }
}
