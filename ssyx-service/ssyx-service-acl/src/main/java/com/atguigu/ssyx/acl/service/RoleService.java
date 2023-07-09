package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.AdminRoleVo;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 00:38
 **/
public interface RoleService extends IService<Role> {

    IPage<Role> selectRolePage(Page<Role> page, RoleQueryVo roleQueryVo);

    /**
     *  根据用户ID获取角色列表
     * @param id 用户ID
     * @return 全部角色列表和用户角色列表
     */
    AdminRoleVo getRolesByAdminId(Long id);

    /**
     * 为用户分配角色
     * @param id 用户ID
     * @param roleIds 角色ID
     */
    void saveAdminRole(Long id, Long[] roleIds);
}
