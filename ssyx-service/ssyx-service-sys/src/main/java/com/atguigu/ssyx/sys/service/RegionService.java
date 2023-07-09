package com.atguigu.ssyx.sys.service;

import com.atguigu.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author yong.qian
 * @since 2023-07-09
 */
public interface RegionService extends IService<Region> {

    List<Region> queryRegionByKeyword(String keyword);
}
