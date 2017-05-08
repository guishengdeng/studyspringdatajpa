package com.biz.service.geo;

import com.biz.core.util.CollectionUtil;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.dao.mysql.po.geo.*;
import com.biz.gbck.dao.mysql.repository.geo.*;
import com.biz.gbck.transform.geo.*;
import com.biz.gbck.vo.common.request.LocationDecodeRequestVo;
import com.biz.gbck.vo.common.response.*;
import com.biz.gbck.vo.geo.AbstractMnsGeoVo;
import com.biz.gbck.vo.geo.GeoTreeVo;
import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.service.geo.interfaces.GeoService;
import com.biz.transformer.geo.RegionToSimpleRegionVo;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by root on 17-5-8.
 */
@Service
@Transactional
public class GeoServiceImpl implements GeoService{
    private static final Logger logger = LoggerFactory.getLogger(GeoService.class);

    @Autowired private DistrictRepository districtRepository;
    @Autowired private CityRepository cityRepository;
    @Autowired private ProvinceRepository provinceRepository;
    @Autowired private RegionRepository regionRespository;
    @Autowired private BusinessRepository businessRepository;

    @Override
    public List<AreaResponseVo> findAllProvince() {
        return null;
    }

    @Override
    public List<AreaResponseVo> findCitiesByProvinceId(Integer provinceId) {
        return null;
    }

    @Override
    public List<AreaResponseVo> findDistrictsByCityId(Integer cityId) {
        return null;
    }

    @Override
    public ProvinceResponseVo findProvince(Integer provinceId) {
        return null;
    }

    @Override
    public CityResponseVo findCity(Integer cityId) {
        return null;
    }

    @Override
    public DistrictResponseVo findDistrict(Integer districtId) {
        return null;
    }

    @Override
    public CityItemResponseVo decodeLocation(LocationDecodeRequestVo reqVo) {
        return null;
    }

    @Override
    public List<ProvinceCitiesItemResponseVo> getProvinceCities() {
        return null;
    }

    @Override
    public List<ProvincesItemResponseVo> getProvinces() {
        return null;
    }

    @Override
    public ProvinceResponseVo findProvinceByBaiduName(String baiduName) {
        return null;
    }

    @Override
    public CityResponseVo findCityByBadiuName(String baiduName) {
        return null;
    }

    @Override
    public DistrictResponseVo findDistrictByBaiduName(String baiduName) {
        return null;
    }

    @Override
    public List<AreaResponseVo> findAllCities() {
        return null;
    }

    @Override
    public List<AreaResponseVo> findAllDistricts() {
        return null;
    }

    @Override
    public void syncGeoDataMysql2Redis() {

    }

    @Override
    public void trans(AbstractMnsGeoVo mnsGeoVo) {

    }

    @Override
    public List<SimpleRegionVo> findRegionByParentAreaLevelAndParentId(Integer areaLevel, Integer parentId) {
        try {
            List data = geoChildrenLoadCache.get(new GeoChildrenLoadKey(areaLevel, parentId));
            return Lists.transform(data, new RegionToSimpleRegionVo());
        } catch (ExecutionException e) {
            e.printStackTrace();
            return newArrayList();
        }
    }

    @Override
    public List<SimpleRegionVo> findRegionByLevel(Integer areaLevel) {
        try {
            List provinces = geoLoadCache.get(IArea.LEVEL_PROVINCE);
            if(CollectionUtils.isNotEmpty(provinces)){
                return Lists.transform(provinces, new RegionToSimpleRegionVo());
            }
            return newArrayList();
        } catch (ExecutionException e) {
            return newArrayList();
        }
    }

    @Override
    public List<GeoTreeVo> findRegionByRegionLevel(Integer level) {
        try {
            switch (level) {
                case IArea.LEVEL_REGION:
                    List<RegionPo> region = regionRespository.findAll();
                    return Lists.transform(region, new RegionPoToGeoTreeVo());
                case IArea.LEVEL_PROVINCE:
                    List<ProvincePo> province = provinceRepository.findAll();
                    return Lists.transform(province, new ProvincePoToGeoTreeVo());
                case IArea.LEVEL_CITY:
                    List<CityPo> cities = cityRepository.findAll();
                    return Lists.transform(cities, new CityPoToGeoTreeVo());
                case IArea.LEVEL_DISTRICT:
                    List<DistrictPo> districts = districtRepository.findAll();
                    return Lists.transform(districts, new DistrictPoToGeoTreeVo());
                case IArea.LEVEL_BUSINESS:
                    List<BusinessPo> businesses = businessRepository.findAll();
                    return Lists.transform(businesses, new BusinessPoToGeoTreeVo());
                default:
                    return newArrayList();
            }

        } catch (Exception e) {
            return newArrayList();
        }
    }

    private LoadingCache<GeoChildrenLoadKey, List> geoChildrenLoadCache =
            CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(30, TimeUnit.MINUTES)
                    .build(new CacheLoader<GeoChildrenLoadKey, List>() {

                        @Override public List load(GeoChildrenLoadKey geoChildrenLoadKey) throws Exception {
                            logger.debug("Load area[{}] data.", geoChildrenLoadKey);
                            switch (geoChildrenLoadKey.level) {
                                case IArea.LEVEL_PROVINCE:
                                    ProvincePo provincePo =
                                            provinceRepository.findOne(geoChildrenLoadKey.id);
                                    return provincePo == null ? newArrayList() : provincePo.getChildren();
                                case IArea.LEVEL_CITY:
                                    CityPo cityPo = cityRepository.findOne(geoChildrenLoadKey.id);
                                    return cityPo == null ? newArrayList() : cityPo.getChildren();
                                case IArea.LEVEL_DISTRICT:
                                    DistrictPo districtPo =
                                            districtRepository.findOne(geoChildrenLoadKey.id);
                                    return districtPo == null ? newArrayList() : districtPo.getChildren();
                                default:
                                    throw DepotnearbyExceptionFactory.GLOBAL.PARAMETER_ERROR;
                            }
                        }
                    });

    private LoadingCache<Integer, List> geoLoadCache =
            CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(30, TimeUnit.MINUTES)
                    .build(new CacheLoader<Integer, List>() {

                        @Override public List load(Integer areaType) throws Exception {
                            logger.debug("Load area[{}] data.", areaType);
                            switch (areaType) {
                                case IArea.LEVEL_REGION:
                                    return regionRespository.findAll();
                                case IArea.LEVEL_PROVINCE:
                                    return provinceRepository.findAll();
                                case IArea.LEVEL_CITY:
                                    return cityRepository.findAll();
                                case IArea.LEVEL_DISTRICT:
                                    return districtRepository.findAll();
                                default:
                                    throw DepotnearbyExceptionFactory.GLOBAL.PARAMETER_ERROR;
                            }
                        }
                    });
    private class GeoChildrenLoadKey {

        public Integer level;

        public Integer id;

        public GeoChildrenLoadKey(Integer level, Integer id) {
            this.level = level;
            this.id = id;
        }
    }
}
