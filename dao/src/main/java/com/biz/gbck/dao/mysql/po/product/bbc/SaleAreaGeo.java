package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.gbck.enums.product.GeoLevelEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * 销售区域和 Geo 关系Po
 *
 * @author david-liu
 * @date 2016年12月16日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_sale_area_geo")
public class SaleAreaGeo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8049148688200666349L;

    /**
     * 销售区域
     */
    @ManyToOne
    @JoinColumn(name = "sale_area_id", nullable = false)
    private SaleArea saleArea;

    /**
     * 区域(省, 市, 销售区域 ID)
     */
    @Column(nullable = false)
    private Long geoId;

    /**
     * 地域等级(可能为省, 市, 销售区域)
     */
    @Column
    @Enumerated(EnumType.STRING)
    private GeoLevelEnum geoLevel;

    public SaleArea getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(SaleArea saleArea) {
        this.saleArea = saleArea;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
    }
}
