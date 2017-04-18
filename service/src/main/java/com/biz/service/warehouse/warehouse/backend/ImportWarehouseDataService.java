package com.biz.service.warehouse.warehouse.backend;

/**
 * @author zhangheng
 * @date 2017/2/15
 * @reviewer
 * @see
 */
public interface ImportWarehouseDataService {

    /**
     * 导入省仓数据到数据库
     */
    void importWarehouse();

    /**
     * 同步省仓数据到redis
     */
    void syncWarehouseToRedis();
}
