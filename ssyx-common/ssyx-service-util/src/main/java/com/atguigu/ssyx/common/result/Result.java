package com.atguigu.ssyx.common.result;

import lombok.Data;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-07 18:16
 **/
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static Result ok() {
        return new Result(ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> ok(T data) {
        return new Result(ResultCodeEnum.SUCCESS, data);
    }

    public static Result fail() {
        return new Result(ResultCodeEnum.FAIL);
    }

    public static Result fail(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum);
    }

    public static Result fail(ResultCodeEnum resultCodeEnum, Object... param) {
        if (ArrayUtils.isNotEmpty(param)) {
            return new Result(resultCodeEnum.getCode(), String.format(resultCodeEnum.getMessage(), param));
        }
        return new Result(resultCodeEnum);
    }

    public static Result fail(Integer code, String message) {
        return new Result(code, message);
    }

    private Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    private Result(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
