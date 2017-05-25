package com.biz.gbck.dao.mysql.po.geo;

import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.common.model.geo.IBusiness;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * 商圈
 *
 * @author defei
 */
@SuppressWarnings("serial") @Entity @Table(name = "geo_business") public class BusinessPo
    extends AbstractAreaWithCode implements IBusiness {
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne(optional = true) @JoinColumn(name = "districtId")
    private DistrictPo district;

    @ManyToOne(optional = true) @JoinColumn(name = "cityId")
    private CityPo city;

    public DistrictPo getDistrict() {
        return district;
    }

    public void setDistrict(DistrictPo district) {
        this.district = district;
    }

    public CityPo getCity() {
        return city;
    }

    public void setCity(CityPo city) {
        this.city = city;
    }

    public Integer getParentId() {
        if (district != null) {
            return district.getId();
        } else
            return null;
    }

    public int getLevel() {
        return IArea.LEVEL_BUSINESS;
    }

    public DistrictPo getParent() {
        return district;
    }

    public List getChildren() {
        return null;
    }

    public double[] getOutBox() {
        if (StringUtils.isNotBlank(coordinate)) {
            double[] result = {180d, -180d, 90d, -90d};
            try {
                String[] arr = coordinate.split("\\|");
                for (String s : arr) {
                    if (StringUtils.isNotBlank(s)) {
                        String[] parr = s.split(";");
                        for (String p : parr) {
                            int idx = p.indexOf(',');
                            double lon = Double.valueOf(p.substring(0, idx).trim());
                            double lat = Double.valueOf(p.substring(idx + 1).trim());
                            result[0] = Math.min(result[0], lon);
                            result[1] = Math.max(result[1], lon);
                            result[2] = Math.min(result[2], lat);
                            result[3] = Math.max(result[3], lat);
                        }
                    }
                }

            } catch (Exception e) {
            }
            return result;
        } else {
            return null;
        }
    }
}
