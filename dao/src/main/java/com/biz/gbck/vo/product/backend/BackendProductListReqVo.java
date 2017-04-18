package com.biz.gbck.vo.product.backend;


import com.biz.gbck.vo.IRequestVo;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/7
 */
public class BackendProductListReqVo implements IRequestVo {

    private static final long serialVersionUID = 8593143644703972209L;

    /**
     * 共通搜索对象 Vo
     *
     * searchKey productName 和 productCode
     */
    private SearchPageVo searchPageVo;

    /**
     * 商家ID
     */
    private Long vendorId;

    public BackendProductListReqVo(SearchPageVo searchPageVo, Long vendorId) {
        this.searchPageVo = searchPageVo;
        this.vendorId = vendorId;
    }

    public BackendProductListReqVo() {
    }

    public SearchPageVo getSearchPageVo() {
        return searchPageVo;
    }

    public void setSearchPageVo(SearchPageVo searchPageVo) {
        this.searchPageVo = searchPageVo;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
