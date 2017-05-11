package com.biz.gbck.dao.redis.repository.geo;


import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.geo.CityRo;
import com.biz.redis.util.RedisUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author jiahao.shen
 * @usage
 * @reviewer
 * @since 2016年9月3日
 */
@Repository
public class CityRedisDao extends CrudRedisDao<CityRo, Integer> {
    /**
     * 保存城市信息到redis
     * @param ro 城市ro
     */
    @Override
    public void save(CityRo ro) {
        Integer provinceId = ro.getProvinceId();
        super.save(ro);
        //如果省id不为空，建立城市id与省id的映射关系
        if (provinceId != null) {
            String key = citiesProvinceIdMappingKey(provinceId);
            zadd(key, ro.getCreateTimestamp(), ro.getId());
            //建立百度地理名称与该城市的映射关系
            this.setCityBaiduNameCityIdMapping(ro.getBaiduname(), ro.getId());
        }
    }

    /**
     * 根据省id查询城市
     * @param provinceId 省份id
     * @return 城市集合
     */
    public List<CityRo> findCitiesByProvinceId(Integer provinceId) {
        logger.debug("query cities by provinceId : {}", provinceId);
        String key = citiesProvinceIdMappingKey(provinceId);
        Set<byte[]> byteIds = zRange(key, 0, -1);
        if(CollectionUtils.isNotEmpty(byteIds)){
            List<CityRo> cityRos = findByIds(byteIds);
            return cityRos;
        }
        logger.debug("query cities by provinceId: {}, result is empty", provinceId);
        return Lists.newArrayList();
    }

    /**
     * 设置百度地理名称与城市id的映射关系
     * @param baiduName 百度城市地理名称
     * @param cityId 城市id
     */
    public void setCityBaiduNameCityIdMapping(String baiduName, Integer cityId) {
        String cityBaiduNameCityIdStringKey = this.baiduNameCityIdMappingKey(baiduName);
        super.set(cityBaiduNameCityIdStringKey, RedisUtil.toByteArray(cityId));
    }

    /**
     * 根据百度地理名称查询城市信息
     * @param baiduName 百度地理名称
     * @return
     */
    public CityRo findCityByBaiduName(String baiduName) {
        String cityBaiduNameMappingKey = this.baiduNameCityIdMappingKey(baiduName);
        byte[] cityId =  super.get(cityBaiduNameMappingKey);
        return super.findOne(RedisUtil.byteArrayToInt(cityId));
    }


    /**
     * 获取城市百度名称和城市ID对应的String Key
     * @param baiduName 城市百度名称
     * @return 百度地理名称与城市的映射key
     */
    private String baiduNameCityIdMappingKey(String baiduName) {
        return getKeyByParams("baiduName", baiduName);
    }

    /**
     * 设置省份与城市的映射key
     * @param provinceId 省份id
     * @return 省份与城市的映射key
     */
    private String citiesProvinceIdMappingKey(Integer provinceId) {
        return getKeyByParams("provinceId", "cities", provinceId);
    }
}
