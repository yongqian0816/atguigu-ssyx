package com.atguigu.ssyx.sys.service.impl;

import com.atguigu.ssyx.common.exception.BizException;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.sys.mapper.RegionWareMapper;
import com.atguigu.ssyx.sys.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author yong.qian
 * @since 2023-07-09
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    @Override
    public IPage<RegionWare> selectPage(Page<RegionWare> page, RegionWareQueryVo regionWareQueryVo) {
        return this.lambdaQuery()
            .like(StringUtils.isNotEmpty(regionWareQueryVo.getKeyword()), RegionWare::getRegionName, regionWareQueryVo.getKeyword())
            .or()
            .like(StringUtils.isNotEmpty(regionWareQueryVo.getKeyword()), RegionWare::getWareName, regionWareQueryVo.getKeyword())
            .page(page);
    }

    @Override
    public void updateRegionWareStatusById(Long id, Integer status) {
        RegionWare regionWare = lambdaQuery().eq(RegionWare::getId, id).one();
        if (regionWare == null) {
            throw new BizException(ResultCodeEnum.RECORD_IS_NOT_EXIST);
        }
        regionWare.setStatus(status);
        updateById(regionWare);
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        Long count = this.lambdaQuery()
            .eq(RegionWare::getRegionId, regionWare.getRegionId())
            .count();
        if (count != 0L) {
            throw new BizException(ResultCodeEnum.RECORD_IS_EXIST);
        }
        this.save(regionWare);
    }
}
