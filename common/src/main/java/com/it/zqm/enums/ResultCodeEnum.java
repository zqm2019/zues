package com.it.zqm.enums;

import lombok.Getter;

/**
 * @Describe: 对外返回code码
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(0, "成功"),
    FAIL_ERROR(400,"失败"),
    CUSTOM_ERROR(409,"业务异常"),
    NOT_FOUND(404, "接口地址不存在"),
    NOT_SUPPORT_METHOD(405, "方法不支持此调用"),
    INTERNAL_SERVER_ERROR(500, "系统异常");

    /**
     * code码
     */
    private int code;

    /**
     * code含义
     */
    private String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
