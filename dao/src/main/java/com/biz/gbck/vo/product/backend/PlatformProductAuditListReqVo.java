package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import java.io.Serializable;

/**
 * 商家审核商品列表请求 Vo
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class PlatformProductAuditListReqVo implements Serializable {

    private static final long serialVersionUID = 6092504230855648021L;

    /**
     * 共通搜索对象 Vo
     */
    private SearchPageVo searchPageVo;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 商品审核状态
     */
    private ProductAuditStatusEnum auditStatus;

    public SearchPageVo getSearchPageVo() {
        return searchPageVo;
    }

    public void setSearchPageVo(SearchPageVo searchPageVo) {
        this.searchPageVo = searchPageVo;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public ProductAuditStatusEnum getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ProductAuditStatusEnum auditStatus) {
        this.auditStatus = auditStatus;
    }
}
