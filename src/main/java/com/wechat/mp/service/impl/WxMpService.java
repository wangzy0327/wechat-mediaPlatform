package com.wechat.mp.service.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.mp.api.impl.WxMpServiceBaseImpl;
import org.springframework.stereotype.Service;

@Service
public class WxMpService extends WxMpServiceBaseImpl {
    @Override
    public Object getRequestHttpClient() {
        return null;
    }

    @Override
    public Object getRequestHttpProxy() {
        return null;
    }

    @Override
    public HttpType getRequestType() {
        return null;
    }

    @Override
    public String getAccessToken(boolean b) throws WxErrorException {
        return null;
    }

    @Override
    public void initHttp() {

    }
}
