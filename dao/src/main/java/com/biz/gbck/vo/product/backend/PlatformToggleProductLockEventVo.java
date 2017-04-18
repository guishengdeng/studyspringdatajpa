package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * 平台后台开关锁定商品事件 Vo
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class PlatformToggleProductLockEventVo implements Serializable {

    private static final long serialVersionUID = -1658366029697329893L;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 是否锁定
     */
    private Boolean locked;

    public PlatformToggleProductLockEventVo(Long vendorId, Long productId, Boolean locked) {
        this.vendorId = vendorId;
        this.productId = productId;
        this.locked = locked;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
