package com.it.zqm.result;

/**
 * @Describe: 统一对外返回Api结构
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
public final class Result {

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回结果具体信息，有错误为null
     */
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
