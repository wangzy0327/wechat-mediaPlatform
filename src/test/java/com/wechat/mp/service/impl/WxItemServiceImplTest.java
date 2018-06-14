package com.wechat.mp.service.impl;

import com.wechat.mp.pojo.WxItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class WxItemServiceImplTest {

    @Autowired
    private WxItemServiceImpl wxItemService;

    @Test
    public void formatUrl() throws Exception {
        String urlStr = "http://a3.rabbitpre.com/m/aUe1ZicDXY";
        if(urlStr.indexOf("https")<0){
            urlStr = urlStr.replace("http","https");
        }
        StringBuffer h5_url = new StringBuffer("");
        if(urlStr.indexOf("/aUe1Zi")>=0&&urlStr.indexOf("/m")>0){
            String newS1 = urlStr.replace("/m/","/m2/");
            System.out.println(newS1);
            h5_url = new StringBuffer("").append(newS1);
        }else{
            h5_url.append(urlStr);
        }
        System.out.println(h5_url.append("?mobile=1").toString());
    }

    @Test
    public void h5Parse() throws Exception {
        /*
        path:http://wangzy.tunnel.qydev.com:80/wechat-tools
        realPath:D:\project\wechat-tools\target\wechat-tools\
        urlStr:http://www.rabbitpre.com/m/FZRbAbR
         */
        String path = "http://wangzy.tunnel.qydev.com:80/wechat-tools";
        String urlStr = "http://www.rabbitpre.com/m/FZRbAbR";
        String realPath = "D:\\project\\wechat-tools\\target\\wechat-tools\\";
        WxItem wxItem = new WxItem();
        wxItemService.H5Parse(path,urlStr,realPath,wxItem);
    }


}