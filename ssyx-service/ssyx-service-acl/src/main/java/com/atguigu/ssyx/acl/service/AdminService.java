package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 02:11
 **/
public interface AdminService extends IService<Admin> {

    IPage<Admin> selectPage(Page<Admin> page, AdminQueryVo adminQueryVo);
}
