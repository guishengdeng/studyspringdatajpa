package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/25
 */
public class ListVendorRelevanceProductReqVo implements Serializable, IRequestVo {

    private static final long serialVersionUID = 8078481348231883591L;

    /**
     * 分页搜索参数(必填)
     *
     * searchKey传product中的字段
     */
    private SearchPageVo searchPageVo;


    /**
     * 商家ID(必填)
     */
    private Long vendorId;

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
