package com.biz.gbck.vo.common.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class ProvincesItemResponseVo implements Serializable {

    private static final long serialVersionUID = -2899886943850821771L;

    private String prefix;

    private List<ProvinceItemResponseVo> provinces;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<ProvinceItemResponseVo> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceItemResponseVo> provinces) {
        this.provinces = provinces;
    }

}
