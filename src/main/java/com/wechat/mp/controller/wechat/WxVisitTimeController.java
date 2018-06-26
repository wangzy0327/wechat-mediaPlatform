package com.wechat.mp.controller.wechat;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.service.IWxVisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Weixin")
public class WxVisitTimeController {

    @Autowired
    private IWxVisitTimeService iWxVisitTimeService;

    @PostMapping("/readWxItem")
    @ResponseBody
    public ServerResponse readWxItem(@RequestParam String openId, @RequestParam String itemId, @RequestParam Integer spendTime){
        return iWxVisitTimeService.readWxItemHandler(openId,itemId,spendTime);
    }

    @PostMapping("/shareAppMessageWxItem")
    @ResponseBody
    public ServerResponse shareAppMessageWxItem(@RequestParam String openId, @RequestParam String itemId){
        return iWxVisitTimeService.shareAppMessageWxItem(openId,itemId);
    }


    @PostMapping("/shareTimeLineWxItem")
    @ResponseBody
    public ServerResponse shareTimeLineWxItem(@RequestParam String openId, @RequestParam String itemId){
        return iWxVisitTimeService.shareTimeLineWxItem(openId,itemId);
    }

}
