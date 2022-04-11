package com.it.zqm.exception;

/**
 * @Describe: 自定义异常基类, 其他类型异常请继承BaseException
 * @Author：zhaqianming
 * @Date: 2021/8/5
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3968657831987616504L;

    private int code = 0;

    public int getCode() {
        return code;
    }

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }


}
