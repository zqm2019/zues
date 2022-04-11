package com.it.zqm.result;

/**
 * @Describe: 统一对外返回Api结构
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
public final class Result {

    private int code;

    private String message;

    private Object data;

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
