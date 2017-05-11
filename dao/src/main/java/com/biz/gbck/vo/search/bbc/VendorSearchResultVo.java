package com.biz.gbck.vo.search.bbc;

import java.io.Serializable;

/**
 * 商户搜素结果
 *
 * @author david-liu
 * @date 2017年01月06日
 * @reviewer
 * @see
 */
public class VendorSearchResultVo implements Serializable {

    private static final long serialVersionUID = -7631451343679822835L;

    /**
     * 商家 ID
     */
    private Long vendorId;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
