package com.atguigu.ssyx.common.result;

import lombok.Getter;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-07 18:15
 **/
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败")
    ;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;
    private final String message;
}
