package com.biz.gbck.vo.depot;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 门店特价Vo
 *
 * @author david-liu
 * @date 2017年02月05日
 * @reviewer
 */
public class DepotPromotionVo implements Serializable {
    private static final long serialVersionUID = -2754667209785138583L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 门店特价
     */
    private Integer promotionPrice;

    /**
     * 促销开始时间
     */
    private Timestamp promotionStartTime;

    /**
     * 促销结束时间
     */
    private Timestamp promotionEndTime;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Timestamp getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Timestamp promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public Timestamp getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(Timestamp promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }
}
