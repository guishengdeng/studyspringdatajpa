package com.biz.service.stock.backend;

import com.biz.gbck.enums.oss.OssType;
import com.biz.gbck.vo.stock.MnsFullDepotStockVo;
import com.biz.gbck.vo.stock.MnsStockChangeVo;
import java.util.List;

/**
 * 库存对接业务
 *
 * @author lei
 * @date 2016年12月10日
 * @reviewer
 */
public interface StockBackendService {

    /**
     * 动态库存是否存在
     */
    boolean existChangeStock(Long changeStockId);

    /**
     * 保存动态库存消息
     */
    void saveChangeStock(MnsStockChangeVo mnsStockChangeVo);

    /**
     * 更新增量库存(门店、城市、全省、全国)
     */
    void updateStock(MnsStockChangeVo mnsStockChangeVo);

    /**
     * 保存全量门店库存
     */
    void saveDepotProductStocks(List<MnsFullDepotStockVo> mnsFullDepotStockVos);


    void reduceLockStock(String orderNo, String depotCode, String productCode, int changeStock);

    /**
     * 定时任务，统计商品库存和城市库存
     */
    void stockStatTask();

    /**
     * 全量库存操作前置接口
     */
    void preHandleFullStocks(OssType type);

    /**
     * 全量库存操作后置接口
     */
    void afterHandleFullStocks(OssType type);

    void releaseLockStocks();
}
