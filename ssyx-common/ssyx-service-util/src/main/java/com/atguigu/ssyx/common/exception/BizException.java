package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-07 21:03
 **/
@Data
public class BizException extends RuntimeException{


    private Integer code;

    private String message;

    public BizException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public BizException(ResultCodeEnum resultCodeEnum, Object... param) {
        this.code = resultCodeEnum.getCode();
        this.message = String.format(resultCodeEnum.getMessage(), param);
    }
}
