package com.biz.gbck.vo.product;

import java.io.Serializable;

/**
 * 商品扩展属性Vo
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public class PropertyItemVo implements Serializable {
    private static final long serialVersionUID = 5917016926644175147L;

    /**
     * 属性ID
     */
    private Long propertyId;

    /**
     * 属性名
     */
    private String propertyName;

    /**
     * 属性值
     */
    private String propertyValue;

    /**
     * 属性值ID
     */
    private Long propertyValueId;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Long getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Long propertyValueId) {
        this.propertyValueId = propertyValueId;
    }
}
