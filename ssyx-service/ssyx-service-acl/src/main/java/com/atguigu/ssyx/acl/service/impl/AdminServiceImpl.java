package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminMapper;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author yong.qian
 * @version 1.0
 * @since 2023-07-09 02:11
 **/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public IPage<Admin> selectPage(Page<Admin> page, AdminQueryVo adminQueryVo) {
        return this.lambdaQuery()
            .eq(StringUtils.isNotEmpty(adminQueryVo.getUsername()), Admin::getUsername, adminQueryVo.getUsername())
            .like(StringUtils.isNotEmpty(adminQueryVo.getName()), Admin::getName, adminQueryVo.getName())
            .page(page);
    }
}
