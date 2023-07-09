package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.common.utils.MD5;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.atguigu.ssyx.vo.acl.AdminRoleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
 * @since 2023-07-09 02:10
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/acl/user")
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    /**
     * 用户列表
     */
    @ApiOperation("用户列表")
    @GetMapping("{current}/{limit}")
    public Result<IPage<Admin>> pageList(@PathVariable Long current,
        @PathVariable Long limit, AdminQueryVo adminQueryVo) {
        Page<Admin> page = new Page<>(current, limit);
        IPage<Admin> pageModel = null;
        try {
            pageModel = adminService.selectPage(page, adminQueryVo);
        } catch (Exception e) {
            log.error("查询用户列表失败:", e);
            return Result.fail(ResultCodeEnum.FAIL);
        }
        return Result.ok(pageModel);
    }

    /**
     *  id查询用户
     */
    @ApiOperation("根据ID查询用户")
    @GetMapping("get/{id}")
    public Result<Admin> get(@PathVariable Long id) {
        return Result.ok(adminService.getById(id));
    }

    /**
     * 添加用户
     */
    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        admin.setPassword(MD5.encrypt(admin.getPassword()));
        adminService.save(admin);
        return Result.ok();
    }

    /**
     *  删除用户
     */
    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody Admin admin) {
        if (admin.getId() == null) {
            return Result.fail(ResultCodeEnum.PARAM_IS_NULL, "id");
        }
        adminService.updateById(admin);
        return Result.ok();
    }

    @ApiOperation("根绝ID删除用户")
    @GetMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        adminService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(("批量删除用户"))
    @PostMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Result.fail(ResultCodeEnum.PARAM_IS_NULL, "id");
        }
        adminService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("根据用户ID获取角色列表")
    @GetMapping("toAssign/{id}")
    public Result<AdminRoleVo> toAssign(@PathVariable Long id) {
        AdminRoleVo result = null;
        try {
            result = roleService.getRolesByAdminId(id);
        } catch (Exception e) {
            log.error("call toAssign exception: ", e);
            return Result.fail();
        }
        return Result.ok(result);
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("doAssign")
    public Result assignRoles(@RequestParam("adminId") Long id,
        @RequestParam("roleId") Long[] roleIds) {
        roleService.saveAdminRole(id, roleIds);
        return Result.ok();
    }

}
