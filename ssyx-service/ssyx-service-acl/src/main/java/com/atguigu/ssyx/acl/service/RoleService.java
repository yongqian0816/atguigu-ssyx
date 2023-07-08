package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Role;
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
}
