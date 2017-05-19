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

    private ProductShowStatus showStatus;

    public PurchaseProductItemVO() {
    }

    public PurchaseProductItemVO(ProductAppListItemVo itemVo, ProductShowStatus showStatus) {
        super(itemVo);
        this.showStatus = showStatus;
    }

    public ProductShowStatus getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(ProductShowStatus showStatus) {
        this.showStatus = showStatus;
    }
}
