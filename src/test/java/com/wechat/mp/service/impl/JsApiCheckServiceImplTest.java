package com.wechat.mp.service.impl;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.service.IJsApiCheckService;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.JSConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class JsApiCheckServiceImplTest {

    @Autowired
    private IJsApiCheckService ijsApiCheckService;

    @Test
    public void getJsApiTicket() throws Exception {
        String url = "http://wangzy.tunnel.qydev.com/wechat-tools/page/push/FvmIZ3i.html";
        System.out.println("accessToken:"+WxApiClient.getAccessToken());
        ServerResponse<JSConfig> jsconfig = ijsApiCheckService.getJsApiTicket(url);
        System.out.println(jsconfig.getData().toString());
    }

}