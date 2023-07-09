package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 14:19
 **/
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @ApiOperation("获取菜单列表")
    @GetMapping("list")
    public Result<List<Permission>> list() {
        List<Permission> permissions = null;
        try {
            permissions = permissionService.queryAllPermissions();
        } catch (Exception e) {
            log.error("call permissionController list exception:", e);
            return Result.fail();
        }

        return Result.ok(permissions);
    }

    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok();
    }

    @ApiOperation(("修改菜单"))
    @PostMapping("update")
    public Result update(@RequestBody Permission permission) {
        if (permission.getId() == null ) {
            return Result.fail(ResultCodeEnum.PARAM_IS_NULL, "id");
        }
        permissionService.updateById(permission);
        return Result.ok();
    }

    @ApiOperation("根据ID删除菜单")
    @GetMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removePermission(id);
        return Result.ok();
    }
    @ApiOperation("查询当前角色拥有的权限")
    @GetMapping("toAssign/{id}")
    public Result<List<Permission>> toAssign(@PathVariable Long id) {
        List<Permission> permissionList = null;
        try {
            permissionList = permissionService.queryRoleAllPermission(id);
        } catch (Exception e) {
            log.error("call PermissionController toAssign exception:", e);
            return Result.fail();
        }
        return Result.ok(permissionList);
    }

    @ApiOperation("为角色分配权限")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long roleId,
        @RequestParam Long[] permissionId) {
        try {
            permissionService.assignPermission(roleId, permissionId);
        } catch (Exception e) {
            log.error("call PermissionController doAssign exception:", e);
            return Result.fail();
        }
        return Result.ok();
    }
}
