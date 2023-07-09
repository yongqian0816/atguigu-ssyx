package com.atguigu.ssyx.sys.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.sys.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author yong.qian
 * @since 2023-07-09
 */
@Api(tags = "开通区域")
@RestController
@RequestMapping("/admin/sys/regionWare")
@Slf4j
public class RegionWareController {

    @Autowired
    RegionWareService regionWareService;

    @ApiOperation("开通区域列表")
    @GetMapping("{current}/{limit}")
    public Result<IPage<RegionWare>> pageList(@PathVariable Long current,
        @PathVariable Long limit, RegionWareQueryVo regionWareQueryVo) {
        Page<RegionWare> page = new Page<>(current, limit);
        IPage<RegionWare> pageModel = regionWareService.selectPage(page, regionWareQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result<RegionWare> get(@PathVariable Long id) {
        return Result.ok(regionWareService.getById(id));
    }

    @ApiOperation("保存开通区域")
    @PostMapping("save")
    public Result save(@RequestBody RegionWare regionWare) {
        regionWareService.saveRegionWare(regionWare);
        return Result.ok();
    }

    @ApiOperation("删除开通区域")
    @GetMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        regionWareService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("修改开通区域状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        regionWareService.updateRegionWareStatusById(id, status);
        return Result.ok();
    }




}

