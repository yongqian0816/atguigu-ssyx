package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result pageList(@PathVariable Long current,
        @PathVariable Long limit, RoleQueryVo roleQueryVo) {
        //1. 创建page对象，传递当前页和每页记录数
        Page<Role> page = new Page<>(current, limit);
        //2. 调用service 方法实现条件分页查询，返回分页对象
        IPage<Role> pageModel = roleService.selectRolePage(page, roleQueryVo);
        return Result.ok(pageModel);
    }

    //2. 根据id查询角色

    //3.添加角色

    //4.修改角色

    //5.根据ID删除角色

    //6.批量删除角色

}
