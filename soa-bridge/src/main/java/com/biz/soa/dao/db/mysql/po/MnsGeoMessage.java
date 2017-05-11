package com.biz.soa.dao.db.mysql.po;

import com.biz.gbck.enums.geo.GeoLevel;

import javax.persistence.*;

/**
 * Mns geo消息
 *
 * @author lei
 * @date 2016/12/22日
 * @reviewer
 * @see
 */
@Entity
@Table(name = "mns_geo_message")
public class MnsGeoMessage extends AbstractMnsEntity {

    private static final long serialVersionUID = 1110585799707516622L;

    @Column(columnDefinition = "TINYINT", nullable = false)
    @Enumerated(EnumType.STRING)
    private GeoLevel geoLevel;

    public GeoLevel getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevel geoLevel) {
        this.geoLevel = geoLevel;
    }
}
