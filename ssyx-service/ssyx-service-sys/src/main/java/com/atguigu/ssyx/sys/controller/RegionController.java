package com.atguigu.ssyx.sys.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.Region;
import com.atguigu.ssyx.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author yong.qian
 * @since 2023-07-09
 */
@Api(tags = "区域模块")
@RestController
@RequestMapping("/admin/sys/region")
@Slf4j
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation("根据区域关键字查询区域列表")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result<List<Region>> queryRegionByKeyword(@PathVariable String keyword) {
        return Result.ok(regionService.queryRegionByKeyword(keyword));
    }
}

