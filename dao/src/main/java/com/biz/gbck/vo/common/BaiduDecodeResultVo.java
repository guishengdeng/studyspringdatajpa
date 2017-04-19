package com.biz.gbck.vo.common;

import java.io.Serializable;

/**
 * 百度地图经纬度查询地址结果Vo
 *
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class BaiduDecodeResultVo implements Serializable {

    private static final long serialVersionUID = 602418771814619483L;

    /*private LocationVo location;

    private String formatted_address;

    private String business;*/

    private BaiduAddressComponentVo addressComponent;

    /*private List<String> pois;

    private List<String> poiRegions;

    private String sematic_description;

    private Integer cityCode;*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /*public LocationVo getLocation() {
        return location;
    }

    public void setLocation(LocationVo location) {
        this.location = location;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }*/

    public BaiduAddressComponentVo getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(BaiduAddressComponentVo addressComponent) {
        this.addressComponent = addressComponent;
    }

   /* public List<String> getPois() {
        return pois;
    }

    public void setPois(List<String> pois) {
        this.pois = pois;
    }

    public List<String> getPoiRegions() {
        return poiRegions;
    }

    public void setPoiRegions(List<String> poiRegions) {
        this.poiRegions = poiRegions;
    }

    public String getSematic_description() {
        return sematic_description;
    }

    public void setSematic_description(String sematic_description) {
        this.sematic_description = sematic_description;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }*/
}
