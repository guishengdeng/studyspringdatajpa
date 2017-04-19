package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * 扩展属性 Vo
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
public class ExtendVo implements Serializable {

    private static final long serialVersionUID = -6890665070948352409L;

    private String extendId;

    private String propertyId;

    public String getExtendId() {
        return extendId;
    }

    public void setExtendId(String extendId) {
        this.extendId = extendId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }


}
