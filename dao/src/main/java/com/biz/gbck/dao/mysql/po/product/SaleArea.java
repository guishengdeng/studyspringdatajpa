package com.biz.gbck.dao.mysql.po.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * 销售区域
 *
 * @author david-liu
 * @date 2016年12月16日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_sale_area", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "vendor_id"})})
public class SaleArea extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -185311054571095632L;

    /**
     * 名称
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 区域编码
     */
    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code;

    /**
     * 描述
     */
    @Column
    private String description;

    /**
     * 销售区域地域集合
     */
    @OneToMany(mappedBy = "saleArea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleAreaGeo> saleAreaGeos;

    /**
     * 商家 ID
     */
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

    /**
     * 状态
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CommonStatusEnum status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SaleAreaGeo> getSaleAreaGeos() {
        return saleAreaGeos;
    }

    public void setSaleAreaGeos(List<SaleAreaGeo> saleAreaGeos) {
        this.saleAreaGeos = saleAreaGeos;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
