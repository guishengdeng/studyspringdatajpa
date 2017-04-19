package com.biz.gbck.vo.common.response;

import java.io.Serializable;


/**
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class ProvinceItemResponseVo implements Serializable {

    private static final long serialVersionUID = -7029515596789614231L;

    private String prefix;

    private String provinceName;

    private Integer provinceId;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "ProvinceItemResponseVo{" +
                "prefix='" + prefix + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}
