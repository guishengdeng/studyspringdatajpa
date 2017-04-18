package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 秒杀商品详情请求Vo
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class SeckillProductDetailReqVo implements Serializable {
    private static final long serialVersionUID = -8250841205708951905L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 秒杀商品价格
     */
    private Integer seckillPrice;

    /**
     * 区域Id
     */
    private Long geoId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Integer seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
