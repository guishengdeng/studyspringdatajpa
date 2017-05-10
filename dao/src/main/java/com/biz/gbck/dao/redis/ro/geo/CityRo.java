package com.biz.gbck.dao.redis.ro.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;

/**
 * 
 * @author shenjiahao
 * @since 2016年9月1日
 *
 */
@Ro(key = "geo:city")
@RoSortedSet(key="list",score="id")
public class CityRo extends AbstractAreaRo implements Comparable<CityRo> {

	private Integer status;
	/*
	 * 省id
	 */
	private Integer provinceId;

	public CityRo() {}

	public CityRo(CityPo po) {
		this.status = po.getStatus();
		this.setProvinceId(po.getProvince() == null ? 0 : po.getProvince().getId());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public int compareTo(CityRo o) {
		return this.prefix.compareTo(o.prefix);
	}
}
