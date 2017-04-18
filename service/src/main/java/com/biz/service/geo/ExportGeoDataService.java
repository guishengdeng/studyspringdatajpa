package com.biz.service.geo;

/**
 * 导出Geo信息
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public interface ExportGeoDataService {

    /**
     * 导出Geo_region信息
     */
    void exportGeoRegion();

    /**
     * 导出Geo_province信息
     */
    void exportGeoProvince();

    /**
     * 导出Geo_city信息
     */
    void exportGeoCity();

    /**
     * 导出Geo_district信息
     */
    void exportGeoDistrict();
}
