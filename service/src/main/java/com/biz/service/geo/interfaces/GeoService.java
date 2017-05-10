package com.biz.service.geo.interfaces;

import com.biz.gbck.vo.common.request.LocationDecodeRequestVo;
import com.biz.gbck.vo.common.response.*;
import com.biz.gbck.vo.geo.AbstractMnsGeoVo;

import java.util.List;

/**
 * geo 公共服务接口
 *
 * @author yanweijin
 * @date 2016/12/16
 */
public interface GeoService {

    /**
     * 查询所有省份信息集合
     *
     * @return 省份信息集合
     */
    List<AreaResponseVo> findAllProvince();

    /**
     * 根据省份id获取该省份下所有的城市信息集合
     *
     * @param provinceId 省份id 必须传入
     * @return 城市信息集合
     */
    List<AreaResponseVo> findCitiesByProvinceId(Integer provinceId);

    /**
     * 根据城市id，查询对应的所有区县信息集合
     *
     * @param cityId 城市id 必须传入
     * @return 区县信息集合
     */
    List<AreaResponseVo> findDistrictsByCityId(Integer cityId);

    /**
     * 根据id获取省份信息
     *
     * @param provinceId 省份id 必须传入
     * @return 省份信息vo
     */
    ProvinceResponseVo findProvince(Integer provinceId);

    /**
     * 根据id获取城市信息
     *
     * @param cityId 城市id 必须传入
     * @return 城市信息vo
     */
    CityResponseVo findCity(Integer cityId);

    /**
     * 根据区/县id获取区县信息
     *
     * @param districtId 区/县id 必须传入
     * @return 区县信息vo
     */
    DistrictResponseVo findDistrict(Integer districtId);

    /**
     * 根据定位解码信息获取定位城市信息
     *
     * @param reqVo 定位解码vo 必须传入
     * @return 定位城市的详细信息
     */
    CityItemResponseVo decodeLocation(LocationDecodeRequestVo reqVo);

    /**
     * 获取各个省份下对应的城市信息，并且将所有的城市信息按照前缀（prefix）分组封装成vo并返回
     *
     * @return 省份对应的城市信息vo
     */
    List<ProvinceCitiesItemResponseVo> getProvinceCities();

    /**
     * 获取各个省份信息，并且将所有的省份信息按照前缀（prefix）分组封装成vo并返回
     *
     * @return 省份信息vo
     */
    List<ProvincesItemResponseVo> getProvinces();

    /**
     * 根据百度地理名称获取身份信息
     *
     * @param baiduName 百度地理名称 必须传入，不能为null或者是“”
     * @return 省份vo
     */
    ProvinceResponseVo findProvinceByBaiduName(String baiduName);

    /**
     * 根据百度地理名称获取城市信息
     *
     * @param baiduName 百度地理名称 必须传入，不能为null或者是“”
     * @return 城市vo
     */
    CityResponseVo findCityByBadiuName(String baiduName);

    /**
     * 根据百度地理名称查询区县信息
     *
     * @param baiduName 百度地理名称
     * @return 区县vo
     */
    DistrictResponseVo findDistrictByBaiduName(String baiduName);

    /**
     * 查询所有城市
     */
    List<AreaResponseVo> findAllCities();

    /**
     * 获取所有区
     */
    List<AreaResponseVo> findAllDistricts();

    /**
     * 将geo数据从数据库同步到redis
     */
    void syncGeoDataMysql2Redis();

    /**
     * 从中台同步geo数据接口
     *
     * @param mnsGeoVo 中台geo数据vo
     */
    void trans(AbstractMnsGeoVo mnsGeoVo);


}
