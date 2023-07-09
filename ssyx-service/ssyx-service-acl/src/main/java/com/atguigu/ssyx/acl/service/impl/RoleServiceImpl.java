package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.RoleMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.exception.BizException;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.AdminRoleVo;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 00:39
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final AdminRoleService adminRoleService;

    public RoleServiceImpl(@Qualifier("adminRoleServiceImpl") AdminRoleService adminRoleService) {
        this.adminRoleService = adminRoleService;
    }

    @Override
    public IPage<Role> selectRolePage(Page<Role> page, RoleQueryVo roleQueryVo) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(roleQueryVo.getRoleName()), Role::getRoleName, roleQueryVo.getRoleName());
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public AdminRoleVo getRolesByAdminId(Long id) {

        List<Role> roles = list();

        List<AdminRole> adminRoles = adminRoleService.lambdaQuery()
            .eq(AdminRole::getAdminId, id)
            .list();

        //查询出用户已经有的角色列表
        List<Long> userRoleIds = adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        List<Role> assignRoles = roles.stream().filter(role -> userRoleIds.contains(role.getId())).collect(Collectors.toList());

        return AdminRoleVo.builder()
            .allRoleList(roles)
            .assignRoles(assignRoles).build();
    }

    @Override
    public void saveAdminRole(Long id, Long[] roleIds) {
        if (ArrayUtils.isEmpty(roleIds)) {
            throw new BizException(ResultCodeEnum.PARAM_IS_NULL, "roleId");
        }
        adminRoleService.remove(new LambdaUpdateWrapper<AdminRole>()
            .eq(AdminRole::getAdminId, id));
        List<AdminRole> adminRoles = Arrays.stream(roleIds).map(roleId -> {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(id);
            adminRole.setRoleId(roleId);
            return adminRole;
        }).collect(Collectors.toList());
        adminRoleService.saveBatch(adminRoles);
    }
}
