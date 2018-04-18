package com.wechat.mp.token;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.junit.Test;

public class TokenTest {
    public static void main(String[] args) {
        String token = new WxMpInMemoryConfigStorage().getToken();
        System.out.println("access_token:{"+token+"}");
    }
}
