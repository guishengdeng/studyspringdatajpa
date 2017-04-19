package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @date 2017/1/17
 * @reviewer
 */
public class ExtendStringVo implements Serializable {

    private static final long serialVersionUID = 8002509729549445713L;

    private String extendName;

    private String propertyName;

    public ExtendStringVo(String extendName, String propertyName) {
        this.extendName = extendName;
        this.propertyName = propertyName;
    }

    public ExtendStringVo() {
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
