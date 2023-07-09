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
    FAIL(201, "失败"),
    PARAM_IS_NULL(202, "请求参数[{}]不能为空"),
    MENU_HAS_CHILDREN_ITEM(203, "该菜单下含有子菜单, 请先移除子菜单"),
    RECORD_IS_EXIST(204, "记录已存在"),
    RECORD_IS_NOT_EXIST(205, "记录不存在");

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;
    private final String message;
}
