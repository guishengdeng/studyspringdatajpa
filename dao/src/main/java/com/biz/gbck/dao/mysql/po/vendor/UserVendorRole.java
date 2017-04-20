package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
@Entity
@Table(name = "ven_user_vendor_role")
public class UserVendorRole extends BaseEntity {

    private static final long serialVersionUID = -7112198098466682910L;

    private Long userId;

    @ManyToOne
    private VendorRole vendorRole;

    @ManyToOne
    private Vendor vendor;

    public VendorRole getVendorRole() {
        return vendorRole;
    }

    public void setVendorRole(VendorRole vendorRole) {
        this.vendorRole = vendorRole;
    }

    public Long getUserId() {

        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }


}
