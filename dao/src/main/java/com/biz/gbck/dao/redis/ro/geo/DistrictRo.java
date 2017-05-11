package com.biz.gbck.dao.redis.ro.geo;

import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;

/**
 * 
 * @author shenjiahao
 * @since 2016年9月1日
 *
 */
@Ro(key = "geo:district")
@RoSortedSet(key="list",score="id")
public class DistrictRo extends AbstractAreaRo {
	
	
	private Integer status;
	/*
	 * 城市id
	 */
	private Integer cityId;
	/*
	 * 省id
	 */
	private Integer provinceId;
	

	public DistrictRo() {}

	public DistrictRo(DistrictPo po){
		this.status = po.getStatus();
		this.setCityId(po.getCity() == null ? 0 : po.getCity().getId());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
}
