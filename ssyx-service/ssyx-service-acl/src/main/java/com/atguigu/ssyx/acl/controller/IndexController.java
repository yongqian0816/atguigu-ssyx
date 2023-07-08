package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-08 11:44
 **/
@RestController
@RequestMapping("/admin/acl/index")
@Api(tags = "登陆")
public class IndexController {

    /**
     * 请求登陆的login
     */
    @PostMapping("login")
    @ApiOperation("登陆接口")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }

    @GetMapping("info")
    @ApiOperation("获取信息")
    public Result getInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    @PostMapping("logout")
    @ApiOperation("退出")
    public Result logout() {
        return Result.ok();
    }
}
