package com.kbryant.quickcore.util;

public class RespBase<T> {
    private int code;
    private int is_alert;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getIs_alert() {
        return is_alert;
    }

    public void setIs_alert(int is_alert) {
        this.is_alert = is_alert;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
