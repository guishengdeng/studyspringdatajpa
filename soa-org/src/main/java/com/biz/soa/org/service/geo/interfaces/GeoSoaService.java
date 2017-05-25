package com.biz.soa.org.service.geo.interfaces;

import com.biz.gbck.vo.geo.SimpleRegionVo;

import java.util.List;

/**
 * geo 公共服务接口
 *
 * @author yanweijin
 * @date 2016/12/16
 */
public interface GeoSoaService {

    /**
     *根据 123(省市县)级别和对应id查询对应集合
     * @return
     */
    List<SimpleRegionVo> findRegionByParentAreaLevelAndParentId(Integer areaLevel,
                                                                Integer parentId);

    /**
     * 根据 1(省)级别，查询省集合 级别暂时没生效
     */
    List<SimpleRegionVo> findRegionByLevel(Integer areaLevel);


}
