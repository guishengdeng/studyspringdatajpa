package com.biz.gbck.vo.geo;

public class MnsGeoCityVo extends AbstractMnsGeoVo {

    private static final long serialVersionUID = -436424557612854895L;

    private Integer provinceId;

    private String hotkeywords;

    public String getHotkeywords() {
        return hotkeywords;
    }

    public void setHotkeywords(String hotkeywords) {
        this.hotkeywords = hotkeywords;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
