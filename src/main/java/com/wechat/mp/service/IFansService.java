package com.wechat.mp.service;

import com.wechat.mp.common.ServerResponse;

import java.io.UnsupportedEncodingException;

public interface IFansService {
    ServerResponse loadMap() throws UnsupportedEncodingException;
}
