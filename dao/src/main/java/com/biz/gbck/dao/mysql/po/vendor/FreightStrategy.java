package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.po.product.Vendor;
import com.biz.gbck.vo.vendor.FreightStrategyCalculationModel;
import com.biz.support.jpa.po.BaseEntity;
import java.util.List;
import javax.persistence.*;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
@Entity
@Table(name = "ven_freight_strategy")
public class FreightStrategy extends BaseEntity implements FreightStrategyCalculationModel {


    //当前运费策略对应的快递方式
    @OneToMany(mappedBy = "freightStrategy")
    private List<VendorExpress> vendorExpresses;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    //模板名称
    @Column(length = 20, nullable = false)
    private String template;

    //是否启用,ture:是,false:否
    private boolean enable = true;

    //首重,单位克
    @Column(length = 10, nullable = false)
    private Integer firstWeight;

    //续重,单位克
    @Column(length = 10, nullable = false)
    private Integer nextWeight;

    //首费,单位分
    @Column(length = 10, nullable = false)
    private Integer firstPrice;

    //续费,单位分
    @Column(length = 10, nullable = false)
    private Integer nextPrice;

    //满 xxx 包邮,如果设置为0,则无条件包邮,如果设置为负数,则不包邮;单位分
    @Column(length = 10, nullable = false)
    private Integer freeIfExceedPrice;

    //是否逻辑删除,true:是(删除),false:否
    private boolean deleted = false;


    public List<VendorExpress> getVendorExpresses() {
        return vendorExpresses;
    }

    public void setVendorExpresses(List<VendorExpress> vendorExpresses) {
        this.vendorExpresses = vendorExpresses;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(Integer firstWeight) {
        this.firstWeight = firstWeight;
    }

    public Integer getNextWeight() {
        return nextWeight;
    }

    public void setNextWeight(Integer nextWeight) {
        this.nextWeight = nextWeight;
    }

    public Integer getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Integer firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Integer getNextPrice() {
        return nextPrice;
    }

    public void setNextPrice(Integer nextPrice) {
        this.nextPrice = nextPrice;
    }

    public Integer getFreeIfExceedPrice() {
        return freeIfExceedPrice;
    }

    public void setFreeIfExceedPrice(Integer freeIfExceedPrice) {
        this.freeIfExceedPrice = freeIfExceedPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }


}

