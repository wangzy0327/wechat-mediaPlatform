package com.wechat.mp.controller;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.service.IJsApiCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Weixin")
public class WxJSAPIController {

    @Autowired
    private IJsApiCheckService jsApiCheckService;

    @GetMapping("/jsApiCheck")
    @ResponseBody
    public ServerResponse jsapiCheck(String url){
        return jsApiCheckService.getJsApiTicket(url);
    }

}
