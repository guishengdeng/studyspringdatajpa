package com.biz.gbck.dao.redis.repository.geo;


import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.geo.ProvinceRo;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author jiahao.shen
 * @since 2016年9月3日
 * @usage 
 * @reviewer
 */
@Repository
public class ProvinceRedisDao extends CrudRedisDao<ProvinceRo, Integer> {

	/**
	 * 保存省份信息到redis
	 * @param ro 省份信息ro
	 */
	@Override
	public void save(ProvinceRo ro) {
		super.save(ro);
		//建立省份id与百度地理名称的映射关系
		this.setProvinceBaiduNameMapping(ro.getBaiduname(), ro.getId());
	}

	/**
	 * 设置省份id与百度地理名称的映射关系
	 * @param baiduName 百度地理名称
	 * @param provinceId 省份id
	 */
	public void setProvinceBaiduNameMapping(String baiduName, Integer provinceId) {
		String provinceBaiduNameMappingKey = this.provinceBaiduNameMappingKey(baiduName);
		super.set(provinceBaiduNameMappingKey, RedisUtil.toByteArray(provinceId));
	}

	/**
	 * 根据百度地理名称查询省份信息
	 * @param baiduName 百度地理名称
	 * @return 省份ro
	 */
	public ProvinceRo findProvinceByBaiduName(String baiduName) {
		String provinceBaiduNameProvinceIdStringKey = this.provinceBaiduNameMappingKey(baiduName);
		byte[] provinceId = super.get(provinceBaiduNameProvinceIdStringKey);
		return super.findOne(RedisUtil.byteArrayToInt(provinceId));
	}

	/**
	 * 百度地理名称与省份映射键
	 * @param baiduName 百度地理名称
	 * @return 百度地理名称与省份的映射键
	 */
	private String provinceBaiduNameMappingKey(String baiduName) {
		return getKeyByParams("baiduName", baiduName);
	}
}
