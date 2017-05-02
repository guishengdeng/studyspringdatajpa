package com.biz.gbck.dao.mysql.po.vendor.bbc;

import com.biz.gbck.dao.mysql.po.product.bbc.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
//@Entity
//@Table(name = "ven_vendor_express")
public class VendorExpress extends BaseEntity {

    private static final long serialVersionUID = -5546235757363076391L;

    @ManyToOne
    private SupportExpress supportExpress;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private FreightStrategy freightStrategy;

    private Boolean deleteFlag = Boolean.FALSE;

    public SupportExpress getSupportExpress() {
        return supportExpress;
    }

    public void setSupportExpress(SupportExpress supportExpress) {
        this.supportExpress = supportExpress;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public FreightStrategy getFreightStrategy() {
        return freightStrategy;
    }

    public void setFreightStrategy(FreightStrategy freightStrategy) {
        this.freightStrategy = freightStrategy;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


}
