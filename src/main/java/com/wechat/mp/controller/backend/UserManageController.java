package com.wechat.mp.controller.backend;

import com.wechat.mp.common.Const;
import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.User;
import com.wechat.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录页面
     * @return
     */
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String index(Model model) {
//        return "page/backendLogin";
//    }

    @PostMapping(value = "/login")
//    @ResponseBody
    public String login(String username, String password, HttpServletRequest request, HttpSession session, ModelMap map){
        ServerResponse<User> response = iUserService.login(username, password);
        session.invalidate();
        session = request.getSession();
        if (response.isSuccess()) {
            User u = response.getData();
            if (u.getRole() == Const.Role.ROLE_ADMIN) {
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER, u);
                return "redirect:/backend/wxItem/list";
            } else {
                map.put("message",ServerResponse.createByErrorMessage("不是管理员无法登录!"));
                return "backendLogin";
            }
        }
        map.put("message",response);
        return "backendLogin";
    }
}
