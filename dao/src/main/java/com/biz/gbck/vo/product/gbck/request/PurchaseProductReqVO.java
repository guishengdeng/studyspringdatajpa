package com.biz.gbck.vo.product.gbck.request;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by david-liu on 2017/05/17 00:49.
 */
public class PurchaseProductReqVO implements Serializable {
    private static final long serialVersionUID = -8071552204126008970L;

    /**
     * 商品ID集合
     */
    @NotEmpty(message = "商品ID集合不能为空")
    private List<Long> productIds;

    /**
     * 用户组ID
     */
    @NotNull(message = "用户组ID不能为空")
    private Long companyGroupId;

    /**
     * 上级采购单位ID
     */
    @NotNull(message = "上级采购单位ID不能为空")
    private Long sellerId;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(Long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
