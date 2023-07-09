package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 00:36
 **/
@Api(tags = "角色模块")
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    //1. 角色列表
    @GetMapping("{current}/{limit}")
    @ApiOperation("角色列表接口")
    public Result<IPage<Role>> pageList(@PathVariable Long current,
        @PathVariable Long limit, RoleQueryVo roleQueryVo) {
        //1. 创建page对象，传递当前页和每页记录数
        Page<Role> page = new Page<>(current, limit);
        //2. 调用service 方法实现条件分页查询，返回分页对象
        IPage<Role> pageModel = roleService.selectRolePage(page, roleQueryVo);
        return Result.ok(pageModel);
    }

    //2. 根据id查询角色
    @ApiOperation("根据ID查询角色")
    @GetMapping("get/{id}")
    public Result<Role> get(@PathVariable Long id) {
        return Result.ok(roleService.getById(id));
    }

    //3.添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) {
        roleService.save(role);
        return Result.ok();
    }

    //4.修改角色
    @ApiOperation("修改角色")
    @PostMapping("update")
    public Result update(@RequestBody Role role) {
        if (role == null || role.getId() == null) {
            return Result.fail(ResultCodeEnum.PARAM_IS_NULL, "id");
        }
        roleService.updateById(role);
        return Result.ok();
    }

    //5.根据ID删除角色
    @ApiOperation(value = "根据ID删除角色")
    @GetMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.ok();
    }

    //6.批量删除角色
    @ApiOperation(value = "批量删除")
    @PostMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        roleService.removeByIds(ids);
        return Result.ok();
    }

}
