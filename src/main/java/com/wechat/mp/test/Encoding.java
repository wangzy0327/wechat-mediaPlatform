package com.wechat.mp.test;

import java.nio.charset.Charset;

public class Encoding {
    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset().name());
    }
}
