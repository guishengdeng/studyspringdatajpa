package com.biz.gbck.dao.mysql.po.vendor.bbc;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A类商户中的虚拟店铺与品牌的映射关系
 *
 * @author zhangcheng
 * @date 2017/3/9
 * @reviewer
 * @see
 */
//@Entity
//@Table(name = "ven_vendor_brand_relationship")
public class VirtualVendorWithBrandRelationShip extends BaseEntity {

    private static final long serialVersionUID = -1662276222659367671L;

    /**
     * 虚拟店铺原始Id
     */
    @Column
    private String storeId;

    /**
     * 虚拟店铺名称
     */
    @Column
    private String vendorName;

    /**
     * 虚拟店铺对应的品牌名称
     */
    @Column
    private String brandName;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
