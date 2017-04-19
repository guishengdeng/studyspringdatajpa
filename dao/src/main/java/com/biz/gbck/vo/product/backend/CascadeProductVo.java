package com.biz.gbck.vo.product.backend;

import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Map;

/**
 * 配置商品商品明细Vo
 *
 * @author david-liu
 * @date 2017年02月16日
 * @reviewer
 */
public class CascadeProductVo implements Serializable {
    private static final long serialVersionUID = 9022899229479270509L;

    private String productCode;

    private Map<String, String> properties = Maps.newHashMap();

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
