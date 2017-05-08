package com.biz.gbck.dao.redis.repository.geo;


import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.geo.DistrictRo;
import com.biz.redis.util.RedisUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author jiahao.shen
 * @since 2016年9月3日
 * @usage 
 * @reviewer
 */
@Repository
public class DistrictRedisDao extends CrudRedisDao<DistrictRo, Integer> {
	/**
	 * 保存区/县信息到redis
	 * @param ro 区/县信息ro
	 */
	@Override
	public void save(DistrictRo ro){
		Integer cityId = ro.getCityId();
		super.save(ro);
		//如果城市id不为空，建立城市与区/县的映射关系
		if (cityId!=null){
			String key = districtsCityIdMappingKey(cityId);
		    zadd(key,ro.getCreateTimestamp(),ro.getId());
		}
		String baiduname = ro.getBaiduname();
		if(StringUtils.isNotBlank(baiduname)){
			//创建区县和百度地理名称的映射关系
            String baiduNameMappingKey = this.districtsBaiduNameMappingKey(baiduname);
            super.set(baiduNameMappingKey, RedisUtil.toByteArray(ro.getId()));
        }
	}

	/**
	 * 根据城市id查询区/县信息集合
	 * @param cityId 城市id
	 * @return 区/县集合
	 */
	public List<DistrictRo> findDistrictByCityId(Integer cityId){
		logger.debug("query district by city id: {}", cityId);
		String key = districtsCityIdMappingKey(cityId);
		Set<byte[]> byteIds = zRange(key, 0, -1);
		if(CollectionUtils.isNotEmpty(byteIds)){
			return super.findByIds(byteIds);
		}
		logger.debug("query district by city id: {} is empty", cityId);
		return Lists.newArrayList();
	}

    /**
     * 根据百度地理名称查询区县信息
     * @param baiduName 百度地理名称
     * @return
     */
    public DistrictRo findDistrictByBaiduName(String baiduName){
        logger.debug("query district by baiduname : {}", baiduName);
        String key = this.districtsBaiduNameMappingKey(baiduName);
        byte[] districtId = super.get(key);
        if(districtId == null) return null;
        return super.findOne(RedisUtil.byteArrayToInt(districtId));
    }


	/**
	 * 创建城市id与区/县的映射的key
	 * @param cityId 城市id
	 * @return 城市id与区/县信息的映射键
	 */
	private String districtsCityIdMappingKey(Integer cityId){
		return getKeyByParams("cityId", "districts", cityId);
	}

	/**
	 * 创建百度地理名称和区县的映射key
	 * @param baiduname 百度地理名称
	 * @return
	 */
	private String districtsBaiduNameMappingKey(String baiduname){
		return getKeyByParams("cityId", "baiduname", baiduname);
	}
}
