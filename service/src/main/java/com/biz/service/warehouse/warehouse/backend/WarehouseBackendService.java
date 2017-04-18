package com.biz.service.warehouse.warehouse.backend;


import com.biz.gbck.vo.warehouse.MnsWarehouseVo;

/**
 * 省仓后端Service
 * @author zhangcheng
 * @date 2017/1/6
 * @reviewer
 * @see
 */
public interface WarehouseBackendService {

    /**
     * 转换中台省仓信息
     * @param mnsWarehouseVo
     */
    void trans(MnsWarehouseVo mnsWarehouseVo);

}
