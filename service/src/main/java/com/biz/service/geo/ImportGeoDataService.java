package com.biz.service.geo;

/**
 * 导入Geo信息服务
 *
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public interface ImportGeoDataService {

    /**
     * 导入Geo_Region信息
     */
    void importGeoRegion();

    /**
     * 导入Geo_Province信息
     */
    void importGeoProvince();

    /**
     * 导入Geo_City信息
     */
    void importGeoCity();

    /**
     * 导入Geo_District信息
     */
    void importGeoDistrict();
}
