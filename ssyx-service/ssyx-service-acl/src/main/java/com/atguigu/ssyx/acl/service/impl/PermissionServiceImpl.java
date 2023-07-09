package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.PermissionMapper;
import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.acl.service.RolePermissionService;
import com.atguigu.ssyx.common.exception.BizException;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 14:20
 **/
@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private final RolePermissionService rolePermissionService;

    public PermissionServiceImpl(@Qualifier("rolePermissionServiceImpl") RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public List<Permission> queryAllPermissions() {
        List<Permission> list = list();
        List<Permission> permissions = list.stream().filter(item -> item.getPid() == 0L).collect(Collectors.toList());
        permissions.forEach(item ->{
            item.setLevel(1);
            assemblePermissionList(item, list);
        });
        return permissions;
    }

    @Override
    public void removePermission(Long id) {
        List<Permission> childrenList = this.lambdaQuery()
            .eq(Permission::getPid, id)
            .list();
        if (!CollectionUtils.isEmpty(childrenList)) {
            throw new BizException(ResultCodeEnum.MENU_HAS_CHILDREN_ITEM);
        }
        this.removeById(id);
    }

    @Override
    public List<Permission> queryRoleAllPermission(Long id) {
        List<Permission> list = this.lambdaQuery().list();
        List<RolePermission> permissions = rolePermissionService.lambdaQuery().eq(RolePermission::getRoleId, id).list();
        List<Permission> collect = list.stream().filter(item -> item.getPid() == 0L).collect(Collectors.toList());
        collect.forEach(item -> {
            item.setLevel(1);
            assemblePermissionList(item, list);
        });
        if (!CollectionUtils.isEmpty(permissions)) {
            List<Long> permissionIdList = permissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
            collect.forEach(item -> checkRolePermission(item, permissionIdList));
        }
        return collect;
    }

    private void checkRolePermission(Permission item, List<Long> permissionIdList) {
        if (permissionIdList.contains(item.getId())) {
            item.setSelect(true);
        }
        item.getChildren().forEach(permission -> checkRolePermission(permission, permissionIdList));
    }

    @Override
    public void assignPermission(Long roleId, Long[] permissionId) {
        rolePermissionService.remove(new LambdaUpdateWrapper<RolePermission>()
            .eq(RolePermission::getRoleId, roleId));
        List<RolePermission> rolePermissions = Arrays.stream(permissionId).map(item -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(item);
            return rolePermission;
        }).collect(Collectors.toList());
        rolePermissionService.saveBatch(rolePermissions);
    }

    private void assemblePermissionList(Permission item, List<Permission> list) {
        List<Permission> permissions = list.stream().filter(x -> item.getId().equals(x.getPid())).collect(Collectors.toList());
        item.setChildren(permissions);
        permissions.forEach(item1 -> {
            item1.setLevel(item.getLevel() + 1);
            assemblePermissionList(item1, list);
        });
    }
}
