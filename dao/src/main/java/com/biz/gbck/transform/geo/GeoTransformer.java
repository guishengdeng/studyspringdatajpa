package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.redis.ro.geo.CityRo;
import com.biz.gbck.dao.redis.ro.geo.DistrictRo;
import com.biz.gbck.dao.redis.ro.geo.ProvinceRo;
import com.google.common.base.Function;

/**
 * 省市区/县 po转ro类
 */
public interface GeoTransformer {

	Province2ProvinceRo PROVINCE_TRANSFORMER = new Province2ProvinceRo();
	City2CityRo CITY_TRANSFORMER = new City2CityRo();
	District2DistrictRo DISTRICT_TRANSFORMER = new District2DistrictRo();
	
	class Province2ProvinceRo implements Function<ProvincePo, ProvinceRo> {
		@Override
		public ProvinceRo apply(ProvincePo input) {
			return new ProvinceRo(input);
		}
	}
	class City2CityRo implements Function<CityPo,CityRo>{
		@Override
		public CityRo apply(CityPo input){
		return new CityRo(input);
		}
	}
	class District2DistrictRo implements Function<DistrictPo,DistrictRo>{
		@Override
		public DistrictRo apply(DistrictPo input){
			return new DistrictRo(input);
		}
	}
}
