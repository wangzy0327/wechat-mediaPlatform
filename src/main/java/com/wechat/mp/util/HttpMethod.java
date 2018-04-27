package com.wechat.mp.util;

public enum HttpMethod {

    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    CONNECT("CONNECT"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    private String methodName;

    HttpMethod(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
