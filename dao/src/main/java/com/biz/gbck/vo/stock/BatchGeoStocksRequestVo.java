package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.product.GeoLevelEnum;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 省市多商品库存请求vo
 *
 * @author lei
 */
public class BatchGeoStocksRequestVo implements Serializable {
    private static final long serialVersionUID = -2467001768308386168L;
    /**
     * 商品编码(必选)
     */
    private List<String> productCodes;

    /**
     * 省、市Id(必选)
     */
    private Integer geoId;

    /**
     * 省市级别
     */
    private GeoLevelEnum geoLevel = GeoLevelEnum.GEO_PROVINCE;

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
