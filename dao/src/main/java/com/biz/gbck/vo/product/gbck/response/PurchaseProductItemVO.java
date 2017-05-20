package com.biz.gbck.vo.product.gbck.response;

import com.biz.gbck.enums.product.ProductShowStatus;
import java.io.Serializable;

/**
 * 进货单商品VO
 *
 * Created by david-liu on 2017/05/19 12:41.
 */
public class PurchaseProductItemVO extends ProductAppListItemVo implements Serializable {
    private static final long serialVersionUID = 5228800915738935970L;

    /**
     * 商品显示状态
     */
    private ProductShowStatus showStatus;

    /**
     * 商品最小起售数量
     */
    private Integer minQuantity;

    /**
     * 商品最大购买数量
     */
    private Integer maxQuantity;

    public PurchaseProductItemVO() {
    }

    public PurchaseProductItemVO(ProductAppListItemVo itemVo, ProductShowStatus showStatus, Integer minQuantity, Integer maxQuantity) {
        super(itemVo);
        this.showStatus = showStatus;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public ProductShowStatus getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(ProductShowStatus showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
