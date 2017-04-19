package com.biz.service.depot;

import com.biz.gbck.vo.depot.DepotPromotionReqVo;
import com.biz.gbck.vo.depot.DepotPromotionRespVo;
import java.io.Serializable;
import java.util.List;

/**
 * 门店特价Service接口
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public interface DepotPromotionService extends Serializable {

    /**
     * 批量获取门店特价
     *
     * @param reqVos 门店特价请求Vo集合
     * @return 门店特价Vo集合
     */
    List<DepotPromotionRespVo> depotPromotions(List<DepotPromotionReqVo> reqVos);

    /**
     * 获取门店特价
     *
     * @param reqVo 门店特价请求Vo
     * @return 门店特价Vo
     */
    DepotPromotionRespVo depotPromotion(DepotPromotionReqVo reqVo);

}
