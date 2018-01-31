package com.kbryant.quickcore.util;

public class ApiException extends Exception {
    private boolean isAlter;
    private int code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int code) {
        this(msg);
        this.code = code;
    }

    public ApiException(String msg, int code, boolean isAlter) {
        this(msg, code);
        this.isAlter = isAlter;
    }

    public boolean isAlter() {
        return isAlter;
    }

    public int getCode() {
        return code;
    }
}
