package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商户删除的商品列表项 Vo
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public class VendorDeletedProductListItemVo implements Serializable {

    private static final long serialVersionUID = -7367740767555570804L;

    /**
     * ID
     */
    private String id;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 删除日期
     */
    private Timestamp deleteTimestamp;

    /**
     * 该商品是否被平台端锁定
     */
    private Boolean locked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getDeleteTimestamp() {
        return deleteTimestamp;
    }

    public void setDeleteTimestamp(Timestamp deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
