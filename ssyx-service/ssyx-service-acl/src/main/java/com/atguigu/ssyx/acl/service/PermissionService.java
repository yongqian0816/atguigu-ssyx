package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 14:20
 **/
public interface PermissionService extends IService<Permission> {

    /**
     * 获取全部菜单
     * @return 菜单列表
     */
    List<Permission> queryAllPermissions();

    /**
     *  删除菜单
     * @param id 菜单ID
     */
    void removePermission(Long id);

    /**
     * 获取角色拥有的所有权限
     * @param id 角色ID
     * @return 权限列表
     */
    List<Permission> queryRoleAllPermission(Long id);

    void assignPermission(Long roleId, Long[] permissionId);
}
