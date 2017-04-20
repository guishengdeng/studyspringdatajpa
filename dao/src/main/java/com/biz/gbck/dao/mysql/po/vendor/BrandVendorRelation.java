package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 品牌和商家关系
 *
 * @author yanweijin
 * @date 2017/3/14
 */
@Entity
@Table(name = "ven_vendor_brand")
public class BrandVendorRelation extends BaseEntity {

    private static final long serialVersionUID = -926557313248231201L;

    @ManyToOne(optional = false)
    private Brand brand;

    @ManyToOne(optional = false)
    private Vendor vendor;

    @Column(nullable = false)
    private Long categoryId;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
