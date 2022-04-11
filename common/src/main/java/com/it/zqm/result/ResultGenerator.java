package com.it.zqm.result;

import com.it.zqm.constants.MessageConstants;
import com.it.zqm.enums.ResultCodeEnum;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
public final class ResultGenerator {
    private ResultGenerator() {

    }

    public static Result getSuccessResult(Object data) {
        return new Result().setCode(ResultCodeEnum.SUCCESS.getCode())
                .setMessage(MessageConstants.DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result getSuccessResult() {
        return new Result().setCode(ResultCodeEnum.SUCCESS.getCode())
                .setMessage(MessageConstants.DEFAULT_SUCCESS_MESSAGE);
    }


    public static Result getFailResult(String message) {
        return new Result().setCode(ResultCodeEnum.FAIL_ERROR.getCode())
                .setMessage(message);
    }

    public static Result getFailResult(int  code, String message) {
        return new Result().setCode(code)
                .setMessage(message);
    }

}
