package com.wechat.mp.controller.backend;

import com.google.gson.Gson;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.controller.common.ValidLogin;
import com.wechat.mp.service.IFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend")
public class HomeController {

    @Autowired
    private IFansService iFansService;

    /**
     * 登录后的页面
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpSession session,ModelMap map) {
//        List<Map<String,Object>> result = customerService.homeTotal();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(result);
//
//        model.addAttribute("json",json);
        if(!ValidLogin.isLogin(session)){
            return "redirect:/";
        }
        return "backend/home";
    }

    /**
     * 加载地图
     * @param request
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/home/map",method = RequestMethod.POST)
    public ServerResponse loadMap(HttpServletRequest request,HttpSession session,ModelMap map){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        try {
            return iFansService.loadMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
