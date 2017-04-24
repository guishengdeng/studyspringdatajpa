package com.biz.gbck.vo.depot;

import java.io.Serializable;

/**
 * 门店特价RespVo
 *
 * @author david-liu
 * @date 2017年02月14日
 * @reviewer
 */
public class DepotPromotionRespVo implements Serializable {
    private static final long serialVersionUID = -834865369125732219L;

    private DepotPromotionVo depotPromotionVo;

    private DepotPromotionVo warehouseDepotPromotionVo;

    public DepotPromotionVo getDepotPromotionVo() {
        return depotPromotionVo;
    }

    public void setDepotPromotionVo(DepotPromotionVo depotPromotionVo) {
        this.depotPromotionVo = depotPromotionVo;
    }

    public DepotPromotionVo getWarehouseDepotPromotionVo() {
        return warehouseDepotPromotionVo;
    }

    public void setWarehouseDepotPromotionVo(DepotPromotionVo warehouseDepotPromotionVo) {
        this.warehouseDepotPromotionVo = warehouseDepotPromotionVo;
    }
}
