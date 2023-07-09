package com.atguigu.ssyx.sys.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.Ware;
import com.atguigu.ssyx.sys.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author yong.qian
 * @since 2023-07-09
 */
@Api(tags = "仓库模块")
@RestController
@RequestMapping("/admin/sys/ware")
public class WareController {

    @Autowired
    private WareService wareService;



    @ApiOperation("查询所有仓库列表")
    @GetMapping
    public Result<List<Ware>> findAllList() {
        return Result.ok(wareService.list());
    }
}

