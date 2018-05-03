package com.wechat.mp.common;

public enum ResponseCode {
    SUCCESS(0,"alert-success", "SUCCESS"),
    ERROR(1,"alert-danger", "ERROR"),
    NEED_LOGIN(10, "alert-info","NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "alert-warning","ILLEGAL_ARGUMENT");

    private final int code;
    private final String status;
    private final String desc;

    ResponseCode(int code, String status,String desc) {
        this.code = code;
        this.status = status;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getStatus() {return this.status;}
}
