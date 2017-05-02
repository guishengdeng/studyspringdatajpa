package com.biz.gbck.vo.search;

import java.util.List;

/**
 * Created by J on 2016/3/26.
 */
public class SearchShopReqVo implements java.io.Serializable {
    private String mobile;
    private Long id;
    private Integer provinceId;
    private Integer cityId;
    private Integer districtId;
    private List<Integer> status;
    private List<Integer> shopTypes;
    private List<Integer> priceTags;
    private List<Integer> businessTags;

    public List<Integer> getSaleAreas() {
        return saleAreas;
    }

    public void setSaleAreas(List<Integer> saleAreas) {
        this.saleAreas = saleAreas;
    }

    public List<Integer> getPriceTags() {
        return priceTags;
    }

    public void setPriceTags(List<Integer> priceTags) {
        this.priceTags = priceTags;
    }

    public List<Integer> getBusinessTags() {
        return businessTags;
    }

    public void setBusinessTags(List<Integer> businessTags) {
        this.businessTags = businessTags;
    }

    private List<Integer> saleAreas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getShopTypes() {
        return shopTypes;
    }

    public void setShopTypes(List<Integer> shopTypes) {
        this.shopTypes = shopTypes;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }


}
