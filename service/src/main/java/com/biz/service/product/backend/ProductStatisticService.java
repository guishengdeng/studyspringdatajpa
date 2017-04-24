package com.biz.service.product.backend;

import com.biz.gbck.vo.product.backend.ProductSalesVo;
import java.util.List;

/**
 * 商品统计Service
 *
 * @author david-liu
 * @date 2017年02月28日
 * @reviewer
 */
public interface ProductStatisticService {

    /**
     * 批量更新商品销售统计信息
     *
     * @param salesVoList 商品销售统计VoList
     */
    void updateProductStatistic(List<ProductSalesVo> salesVoList);

}
