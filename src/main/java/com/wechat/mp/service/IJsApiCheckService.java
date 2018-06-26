package com.wechat.mp.service;

import com.wechat.mp.common.ServerResponse;

public interface IJsApiCheckService {
    ServerResponse getJsApiTicket(String requestUrl);
}
