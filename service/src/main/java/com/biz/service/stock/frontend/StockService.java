package com.biz.service.stock.frontend;

import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;
import java.util.List;

/**
 * 库存业务接口
 *
 * @date 2016年12月15日
 */
public interface StockService {

    /**
     * 单个商品门店、全省、全国库存查询
     *
     * @return 1 depotCode为空，返回该商品全国可用库存， isProvince状态无效;; 2 depotCode不为空, isProvince为false,
     * 返回该门店可用库存; 3 depotCode不为空, isProvince为true, 返回该门店全省可用库存; 4 其他情况, 返回可用库存为0
     */
    StockResponseVo getStock(StockRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 批量商品门店、全省、全国库存查询
     *
     * @return 1 depotCode为空，返回该批商品全国可用库存, isProvince状态无效; 2 depotCode不为空, isProvince为false,
     * 返回该门店集合可用库存; 3 depotCode不为空, isProvince为true, 返回该门店集合全省可用库存; 4 其他情况, 返回0
     */
    List<StockResponseVo> getStocks(BatchStocksRequestVo batchStocksRequestVo);


    /**
     * 批量获取所传该省(市)商品集合对应可用库存
     *
     * @return 1 geoLevel == GeoLevel.GEO_PROVINCE, 返回该省可用库存 2 geoLevel = GeoLevel.GRO_CITY,
     * isProvince=false,  返回该市可用库存 3 geoLevel = GeoLevel.GRO_CITY, isProvince=true, 返回该市所在销售区域省可用库存
     * 4 其他情况, 返回0
     */
    List<GeoStockResponseVo> getGeoStocks(BatchGeoStocksRequestVo batchGeoStocksRequestVo);

    /**
     * 批量订单锁定门店库存请求vo  增减门店锁定库存(快喝模式订单)
     */
    void updateDepotLockStock(List<DepotLockStockRequestVo> depotLockStockRequestVos);

    /**
     * 批量订单区域锁定库存请求vo（B类商品普通送订单)
     */
    void updateGeoLockStock(List<UpdateGeoLockStockRequestVo> updateGeoLockStockRequestVos);

    /**
     * 批量订单商品锁定库存请求vos (A类商品订单)
     */
    void updateLockStocks(List<UpdateLockStockRequestVo> updateLockStockRequestVos);

    /**
     * 批量更新门店库存请求vos
     */
    void updateDepotStocks(List<UpdateDepotStockRequestVo> updateDepotStockRequestVos);

    /**
     * 批量更新geo库存请求vos
     */
    void updateGeoStocks(List<UpdateGeoStockRequestVo> updateGeoStockRequestVos);

    /**
     * 批量更新库存请求vos
     */
    void updateStocks(List<UpdateStockRequestVo> updateStockRequestVos);


}
