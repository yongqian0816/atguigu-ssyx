package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-07 20:57
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //todo 试试删除这个注解是否返回json数据
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result error(BizException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }

}
