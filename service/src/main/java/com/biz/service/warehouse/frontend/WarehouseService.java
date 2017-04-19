package com.biz.service.warehouse.frontend;

import com.biz.gbck.vo.warehouse.WarehouseResponseVo;
import java.util.List;

/**
 * @author zhangcheng
 * @date 2016/12/16
 * @reviewer
 * @see
 */
public interface WarehouseService {

    /**
     * 获取到所有的省仓
     *
     * @return 所有省仓的集合
     */
    List<WarehouseResponseVo> findAll();

    /**
     * 根据省仓编码获取对应的省仓
     *
     * @return 单个省仓的具体信息
     */
    WarehouseResponseVo findByWarehouseCode(String warehouseCode);

    /**
     * 根据省Id对应的省仓
     *
     * @return 单个省仓的具体信息
     */
    WarehouseResponseVo findByProvinceId(Integer provinceId);

}


