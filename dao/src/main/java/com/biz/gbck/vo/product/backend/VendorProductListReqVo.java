package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.IRequestVo;
import java.io.Serializable;

/**
 * 商家商品列表请求 Vo
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
public class VendorProductListReqVo implements Serializable, IRequestVo {

    private static final long serialVersionUID = -5331843114314202235L;

    /**
     * 商家 Id
     */
    private Long vendorId;

    /**
     * 商品上下架状态
     */
    private SaleStatusEnum saleStatus;

    /**
     * 商品审核状态
     */
    private ProductAuditStatusEnum auditStatus;

    /**
     * 共通搜索页 Vo
     */
    private SearchPageVo searchPageVo;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public SearchPageVo getSearchPageVo() {
        return searchPageVo;
    }

    public void setSearchPageVo(SearchPageVo searchPageVo) {
        this.searchPageVo = searchPageVo;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public ProductAuditStatusEnum getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ProductAuditStatusEnum auditStatus) {
        this.auditStatus = auditStatus;
    }
}
