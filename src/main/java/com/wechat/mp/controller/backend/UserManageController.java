package com.wechat.mp.controller.backend;

import com.wechat.mp.common.Const;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.User;
import com.wechat.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录页面
     * @return
     */

    @PostMapping(value = "/login")
    @ResponseBody
    public ServerResponse login(String username, String password, HttpServletRequest request, HttpSession session){

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset().name());

        ServerResponse<User> response = iUserService.login(username, password);
        session.invalidate();
        session = request.getSession();
        if (response.isSuccess()) {
            User u = response.getData();
            if (u.getRole() == Const.Role.ROLE_ADMIN) {
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER, u);
                return ServerResponse.createBySuccess(u.getUsername());
            } else {
                return ServerResponse.createByErrorMessage("不是管理员无法登录!");
            }
        }
        return response;
    }

    /**
     * 安全退出
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        session.invalidate();
        return "backendLogin";
    }

}
