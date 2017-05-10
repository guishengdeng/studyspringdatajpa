package com.biz.service.geo;

import com.alibaba.fastjson.JSON;
import com.biz.core.map.BaiduMapUtil;
import com.biz.core.util.ExecutionUnit;
import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.repository.geo.CityRepository;
import com.biz.gbck.dao.mysql.repository.geo.DistrictRepository;
import com.biz.gbck.dao.mysql.repository.geo.ProvinceRepository;
import com.biz.gbck.dao.redis.repository.geo.CityRedisDao;
import com.biz.gbck.dao.redis.repository.geo.DistrictRedisDao;
import com.biz.gbck.dao.redis.repository.geo.ProvinceRedisDao;
import com.biz.gbck.dao.redis.ro.geo.CityRo;
import com.biz.gbck.dao.redis.ro.geo.DistrictRo;
import com.biz.gbck.dao.redis.ro.geo.ProvinceRo;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.dao.mysql.repository.geo.*;
import com.biz.gbck.transform.geo.*;
import com.biz.gbck.vo.common.request.LocationDecodeRequestVo;
import com.biz.gbck.vo.common.response.*;
import com.biz.gbck.vo.geo.AbstractMnsGeoVo;
import com.biz.gbck.vo.geo.GeoTreeVo;
import com.biz.gbck.vo.geo.MnsGeoCityVo;
import com.biz.gbck.vo.geo.MnsGeoDistrictVo;
import com.biz.gbck.vo.geo.MnsGeoProvinceVo;
import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.geo.interfaces.GeoService;
import com.biz.service.sync.DataSyncService;
import com.biz.service.sync.SyncDefinition;
import com.biz.transformer.geo.RegionToSimpleRegionVo;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author yanweijin
 * @date 2016/12/18
 */
@Service
public class GeoServiceImpl extends AbstractBaseService implements GeoService, DataSyncService {

    private static final Logger logger = LoggerFactory.getLogger(GeoService.class);

    private static final ProvinceRo2ProvinceResponseVo RO_2_PROVINCE_RESPONSE_VO = new ProvinceRo2ProvinceResponseVo();
    private static final CityRo2CityResponseVo RO_2_CITY_RESPONSE_VO = new CityRo2CityResponseVo();
    private static final DistrictRo2DistrictResponseVo RO_2_DISTRICT_RESPONSE_VO = new DistrictRo2DistrictResponseVo();
    private static final AreaRo2AreaResponseVo RO_2_AREA_RESPONSE_VO = new AreaRo2AreaResponseVo();

    @Autowired
    private ProvinceRedisDao provinceRedisDao;

    @Autowired
    private CityRedisDao cityRedisDao;

    @Autowired
    private DistrictRedisDao districtRedisDao;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RegionRepository regionRepository;

    /**
     * 加载所有的省市区县到guava缓存
     */
    private LoadingCache<Integer, List> allGeoAreaCache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(1000).build(new CacheLoader<Integer, List>() {
        @Override
        public List load(Integer areaLevel) throws Exception {
            logger.info("load area info to guava cache by area level {}", areaLevel);
            switch (areaLevel) {
                case IArea.LEVEL_PROVINCE:
                    //从根源过滤港澳台
                    List<ProvinceRo> provinceRos = provinceRedisDao.findAll();
                    if (provinceRos == null) {
                        return newArrayList();
                    } else {
                        Iterable<ProvinceRo> filtered = Iterables.filter(provinceRos, new Predicate<ProvinceRo>() {
                            @Override
                            public boolean apply(ProvinceRo input) {
                                return !input.getName().matches(".*(香港|澳门|台湾).*");
                            }
                        });
                        return Lists.newArrayList(filtered);
                    }
                case IArea.LEVEL_CITY:
                    List<CityRo> cityRos = cityRedisDao.findAll();
                    return cityRos == null ? newArrayList() : cityRos;
                case IArea.LEVEL_DISTRICT:
                    List<DistrictRo> districtRos = districtRedisDao.findAll();
                    return districtRos == null ? newArrayList() : districtRos;
                default:
                    return newArrayList();
            }
        }
    });
    /**
     * 加载省市区县的下级数据到guava缓存
     */
    private LoadingCache<GeoLoadChildrenKey, List> geoChildrenAreaCache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(1000).build(new CacheLoader<GeoLoadChildrenKey, List>() {
        @Override
        public List load(GeoLoadChildrenKey geoLoadChildrenKey) throws Exception {
            logger.info("load children area info to guava cache by key {}", geoLoadChildrenKey);
            switch (geoLoadChildrenKey.getAreaLevel()) {
                case IArea.LEVEL_PROVINCE:
                    List<CityRo> cityRos = cityRedisDao.findCitiesByProvinceId(geoLoadChildrenKey.id);
                    return cityRos == null ? newArrayList() : cityRos;
                case IArea.LEVEL_CITY:
                    List<DistrictRo> districtRos = districtRedisDao.findDistrictByCityId(geoLoadChildrenKey.id);
                    return districtRos == null ? newArrayList() : districtRos;
                default:
                    return newArrayList();
            }
        }
    });

    /**
     * 通过guava级联获取省市区的key
     */
    private class GeoLoadChildrenKey {
        private Integer id;
        private Integer areaLevel;

        public GeoLoadChildrenKey(Integer id, Integer areaLevel) {
            this.id = id;
            this.areaLevel = areaLevel;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAreaLevel() {
            return areaLevel;
        }

        public void setAreaLevel(Integer areaLevel) {
            this.areaLevel = areaLevel;
        }
    }

    /**
     * 返回中国所有省的列表
     * 1.  从 guava 获取所有省 ro 缓存 list
     * 2.  如果获取到的 list 不为空,则将其转换为 volist 返回,否则返回一个空 list
     * 3.  如果出现并发执行异常,记录日志后返回空 list
     *
     * @return 所有省的 volist
     * @author ywj
     */
    @Override
    public List<AreaResponseVo> findAllProvince() {
        logger.debug("find all province");
        try {
            List<ProvinceRo> provinceRos = allGeoAreaCache.get(IArea.LEVEL_PROVINCE);
            if (CollectionUtils.isNotEmpty(provinceRos)) {
                return Lists.transform(provinceRos, RO_2_AREA_RESPONSE_VO);
            }
        } catch (ExecutionException e) {
            logger.error("find all province error {}", e.getMessage(), e);
        }
        return newArrayList();
    }


    /**
     * 根据省id 获取该省所有市的 volist
     * 1.  判断省 id 是否为 null,如果是则返回空 list
     * 2.  根据省 id 和省 level 组合成 cache load key,从guava cache 中获取该省所有市 rolist
     * 3.  如果 rolist 不为空,将其转换为 volist 返回
     * 4.  如果出现异常,记录日志后返回空 list
     *
     * @param provinceId 省份id 必须传入
     * @return 该省对应城市的
     * @author ywj
     */
    @Override
    public List<AreaResponseVo> findCitiesByProvinceId(Integer provinceId) {
        logger.debug("find cities by province id {}", provinceId);
        List<AreaResponseVo> areaResponseVos = newArrayList();
        if (provinceId == null) {
            return areaResponseVos;
        }
        try {
            List<CityRo> citiesRos = geoChildrenAreaCache.get(new GeoLoadChildrenKey(provinceId, IArea.LEVEL_PROVINCE));
            if (CollectionUtils.isNotEmpty(citiesRos)) {
                return Lists.transform(citiesRos, RO_2_AREA_RESPONSE_VO);
            }
        } catch (Exception e) {
            logger.error("find cities by province id {} in guava cache error", provinceId, e);
        }
        return areaResponseVos;
    }

    @Override
    public List<AreaResponseVo> findDistrictsByCityId(Integer cityId) throws NullPointerException {
        logger.debug("find districts by city id {}", cityId);
        List<AreaResponseVo> areaResponseVos = newArrayList();
        if (cityId == null) {
            return areaResponseVos;
        }
        try {
            List<DistrictRo> districtRos = geoChildrenAreaCache.get(new GeoLoadChildrenKey(cityId, IArea.LEVEL_CITY));
            if (CollectionUtils.isNotEmpty(districtRos)) {
                return Lists.transform(districtRos, RO_2_AREA_RESPONSE_VO);
            }
        } catch (Exception e) {
            logger.error("find districts by city id {} in guava cache error", cityId, e);
        }
        return areaResponseVos;
    }

    @Override
    public ProvinceResponseVo findProvince(Integer provinceId) {
        logger.debug("find province by provinceId : {}", provinceId);
        if (provinceId == null) {
            return null;
        }
        ProvinceRo provinceRo = provinceRedisDao.findOne(provinceId);
        return provinceRo != null ? RO_2_PROVINCE_RESPONSE_VO.apply(provinceRo) : null;
    }

    @Override
    public CityResponseVo findCity(Integer cityId) {
        logger.debug("find city by cityId : {}", cityId);
        if (cityId == null) {
            return null;
        }
        CityRo cityRo = cityRedisDao.findOne(cityId);
        return cityRo != null ? RO_2_CITY_RESPONSE_VO.apply(cityRo) : null;
    }

    @Override
    public DistrictResponseVo findDistrict(Integer districtId) {
        logger.debug("find district by districtId: {}", districtId);
        if (districtId == null) {
            return null;
        }
        DistrictRo districtRo = districtRedisDao.findOne(districtId);
        return districtRo != null ? RO_2_DISTRICT_RESPONSE_VO.apply(districtRo) : null;
    }

    @Override
    public CityItemResponseVo decodeLocation(LocationDecodeRequestVo reqVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Start Decode location, latitude=[{}], longitude=[{}]", reqVo.getLatitude(), reqVo.getLongitude());
        }
        if (reqVo == null) {
            return null;
        }

        Double lat = reqVo.getLatitude() != null ? reqVo.getLatitude().doubleValue() : Double.MIN_VALUE;
        Double lon = reqVo.getLongitude() != null ? reqVo.getLongitude().doubleValue() : Double.MIN_VALUE;
        BaiduDecodeRespVo decodeRespVo;
        if (Double.MIN_VALUE == lat && Double.MIN_VALUE == lon) {
//            throw new BizSystemException("cannot decode location, because the user disabled location");
            return null;
        } else {
            String decodeResp = BaiduMapUtil.getLocationDecode(lat, lon);
            if (logger.isDebugEnabled()) {
                logger.debug("Decode location from baidu, infomation=[{}]", decodeResp);
            }
            decodeRespVo = JsonUtil.json2Obj(decodeResp, BaiduDecodeRespVo.class);
        }
        if (decodeRespVo.getResult() == null
                || decodeRespVo.getResult().getAddressComponent() == null
                || decodeRespVo.getResult().getAddressComponent().getCity() == null
                || decodeRespVo.getResult().getAddressComponent().getProvince() == null) {
            // throw new BizSystemException("cannot decode location from baidu, because result incomplete");
            return null;
        }
        String cityBaiduName = decodeRespVo.getResult().getAddressComponent().getCity();
        String provinceBaiduName = decodeRespVo.getResult().getAddressComponent().getProvince();
        CityRo cityRo = cityRedisDao.findCityByBaiduName(cityBaiduName);
        ProvinceRo provinceRo = provinceRedisDao.findProvinceByBaiduName(provinceBaiduName);
        return getCityItemVo(provinceRo, cityRo);
    }

    @Override
    public List<ProvinceCitiesItemResponseVo> getProvinceCities() {
        logger.debug("start find provinces cities ...");
        List<ProvinceRo> provinceRos;
        try {
            // TODO: 17-3-17 导入数据用guava貌似有问题，这里直接改为走redis
//            provinceRos = allGeoAreaCache.get(IArea.LEVEL_PROVINCE);
            provinceRos = provinceRedisDao.findAll();
        } catch (Exception e) {
            logger.error("get province ros from cache error.", e);
            provinceRos = newArrayList();
        }
        List<CityRo> allCities = newArrayList();
        if (CollectionUtils.isNotEmpty(provinceRos)) {
            for (ProvinceRo provinceRo : provinceRos) {
                if (provinceRo != null) {
                    List<CityRo> cityRos;
                    try {
                        /*cityRos = geoChildrenAreaCache.get(new GeoLoadChildrenKey(provinceRo.getId(), IArea
                                .LEVEL_PROVINCE));*/
                        cityRos = cityRedisDao.findCitiesByProvinceId(provinceRo.getId());
                    } catch (Exception e) {
                        logger.error("get city ros from cache error.", e);
                        cityRos = newArrayList();
                    }
                    allCities.addAll(cityRos);
                }
            }
        }
        Collections.sort(allCities);
        //将相同前缀的城市放入map中
        Map<String, List<CityItemResponseVo>> cityItemMap = Maps.newLinkedHashMap();
        for (CityRo cityRo : allCities) {
            List<CityItemResponseVo> cityItemVos = cityItemMap.get(cityRo.getPrefix());
            if (cityItemVos == null) {
                cityItemVos = newArrayList();
            }
            cityItemVos.add(this.getCityItemVo(provinceRedisDao.findOne(cityRo.getProvinceId()), cityRo));
            cityItemMap.put(cityRo.getPrefix(), cityItemVos);
        }
        List<ProvinceCitiesItemResponseVo> itemVos = newArrayList();
        for (Map.Entry<String, List<CityItemResponseVo>> entry : cityItemMap.entrySet()) {
            ProvinceCitiesItemResponseVo itemVo = new ProvinceCitiesItemResponseVo();
            itemVo.setPrefix(entry.getKey());
            itemVo.setCities(entry.getValue());
            itemVos.add(itemVo);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("返回省下城市列表大小: {},content:{}", itemVos.size(), itemVos);

        }
        return itemVos;
    }

    @Override
    public ProvinceResponseVo findProvinceByBaiduName(String baiduName) {
        logger.debug("find province by baiduname : {}", baiduName);
        if (StringUtils.isBlank(baiduName)) {
            return null;
        }
        ProvinceRo provinceRo = provinceRedisDao.findProvinceByBaiduName(baiduName);
        return provinceRo != null ? RO_2_PROVINCE_RESPONSE_VO.apply(provinceRo) : null;
    }

    @Override
    public CityResponseVo findCityByBadiuName(String baiduName) {
        logger.debug("find city by baiduname : {}", baiduName);
        if (StringUtils.isBlank(baiduName)) {
            return null;
        }
        CityRo cityRo = cityRedisDao.findCityByBaiduName(baiduName);
        return cityRo != null ? RO_2_CITY_RESPONSE_VO.apply(cityRo) : null;
    }

    @Override
    public DistrictResponseVo findDistrictByBaiduName(String baiduName) throws NullPointerException {
        logger.debug("find district by baiduname : {}", baiduName);
        if (StringUtils.isBlank(baiduName)) {
            return null;
        }
        DistrictRo districtRo = districtRedisDao.findDistrictByBaiduName(baiduName);
        logger.debug("current district is [{}]", JSON.toJSONString(districtRo));
        DistrictResponseVo resp = RO_2_DISTRICT_RESPONSE_VO.apply(districtRo);
        return resp;
    }

    @Override
    public List<AreaResponseVo> findAllCities() {
        logger.debug("find all cities");
        List<CityRo> cityRos = cityRedisDao.findAll();
        if (CollectionUtils.isNotEmpty(cityRos)) {
            return Lists.transform(cityRos, RO_2_AREA_RESPONSE_VO);
        }
        return newArrayList();
    }

    @Override
    public List<AreaResponseVo> findAllDistricts() {
        List<DistrictRo> districtRos = districtRedisDao.findAll();
        if (districtRos.isEmpty()) {
            return Collections.emptyList();
        }
        return Lists.transform(districtRos, RO_2_AREA_RESPONSE_VO);
    }

    /**
     * 构建城市详情相应vo
     *
     * @param provinceRo
     * @param cityRo
     * @return
     */
    private CityItemResponseVo getCityItemVo(ProvinceRo provinceRo, CityRo cityRo) {
        CityItemResponseVo cityItemVo = new CityItemResponseVo();
        cityItemVo.setCityId(cityRo.getId());
        cityItemVo.setProvinceId(provinceRo.getId());
        cityItemVo.setProvinceName(provinceRo.getName());
        cityItemVo.setCityName(cityRo.getName());
        cityItemVo.setPrefix(cityRo.getPrefix());
        return cityItemVo;
    }

    private ProvinceItemResponseVo getProvinceItemVo(ProvinceRo provinceRo) {
        ProvinceItemResponseVo provinceItemVo = new ProvinceItemResponseVo();

        provinceItemVo.setProvinceId(provinceRo.getId());
        provinceItemVo.setProvinceName(provinceRo.getName());
        provinceItemVo.setPrefix(provinceRo.getPrefix());

        return provinceItemVo;
    }

    @Override
    public void syncGeoDataMysql2Redis() {
        this.syncMysql2Redis();
    }

    @Override
    @SyncDefinition(syncId = "geoSync", desc = "同步省市区从PO到RO")
    public void syncMysql2Redis() {
        logger.info("开始同步GEO信息到Redis.");
        DataSyncService.DataSyncHelper.syncData(
                provinceRepository, provinceRedisDao, GeoTransformer.PROVINCE_TRANSFORMER);
        DataSyncService.DataSyncHelper.syncData(
                cityRepository, cityRedisDao, GeoTransformer.CITY_TRANSFORMER);
        DataSyncService.DataSyncHelper.syncData(
                districtRepository, districtRedisDao, GeoTransformer.DISTRICT_TRANSFORMER);
        logger.info("同步GEO信息到Redis完成.");
    }

    @Override
    @Transactional
    public void trans(AbstractMnsGeoVo mnsGeoVo) {
        logger.debug("开始转换Mns Geo 数据. vo: {}", mnsGeoVo);
        if (mnsGeoVo == null || mnsGeoVo.getId() == null) {
            logger.warn("中台Geo信息转换，Geo信息无效. Vo: {}", mnsGeoVo);
            return;
        }

        if (mnsGeoVo instanceof MnsGeoProvinceVo) {
            ProvincePo province = provinceRepository.findOne(mnsGeoVo.getId());
            province = province != null ? province : new ProvincePo();
            province = (ProvincePo) new MnsGeoProvinceVo2Province(province).apply(mnsGeoVo);
            provinceRepository.save(province);
            final ProvincePo finalProvince = province;
            delayExecuteRedisOpt(new ExecutionUnit() {
                @Override
                public void execute() {
                    provinceRedisDao.save(GeoTransformer.PROVINCE_TRANSFORMER.apply(finalProvince));
                }
            });
        } else if (mnsGeoVo instanceof MnsGeoCityVo) {
            CityPo city = cityRepository.findOne(mnsGeoVo.getId());
            city = city != null ? city : new CityPo();
            city = (CityPo) new MnsGeoCityVo2City(city).apply(mnsGeoVo);
            cityRepository.save(city);
            final CityPo finalCity = city;
            delayExecuteRedisOpt(new ExecutionUnit() {
                @Override
                public void execute() {
                    cityRedisDao.save(GeoTransformer.CITY_TRANSFORMER.apply(finalCity));
                }
            });
        } else if (mnsGeoVo instanceof MnsGeoDistrictVo) {
            DistrictPo district = districtRepository.findOne(mnsGeoVo.getId());
            district = district != null ? district : new DistrictPo();
            district = (DistrictPo) new MnsGeoDistrictVo2District(district).apply(mnsGeoVo);
            districtRepository.save(district);
            final DistrictPo finalDistrict = district;
            delayExecuteRedisOpt(new ExecutionUnit() {
                @Override
                public void execute() {
                    districtRedisDao.save(GeoTransformer.DISTRICT_TRANSFORMER.apply(finalDistrict));
                }
            });
        }
        logger.debug("完成转换Mns Geo 数据. vo: {}", mnsGeoVo);
    }

    @Override
    public List<ProvincesItemResponseVo> getProvinces() {
        logger.debug("start get provinces ...");
        List<ProvinceRo> provinceRos;
        try {
            provinceRos = allGeoAreaCache.get(IArea.LEVEL_PROVINCE);
        } catch (Exception e) {
            logger.error("get province ros from cache error.", e);
            provinceRos = newArrayList();
        }
        Map<String, List<ProvinceItemResponseVo>> provinceItemMap = Maps.newLinkedHashMap();
        for (ProvinceRo provinceRo : provinceRos) {
            List<ProvinceItemResponseVo> provinceItemVos = provinceItemMap.get(provinceRo.getPrefix());
            if (provinceItemVos == null) {
                provinceItemVos = newArrayList();
            }
            String name = provinceRo.getName();
            provinceRo.setPrefix(getFirstLetter(name));
            provinceItemVos.add(this.getProvinceItemVo(provinceRo));
            provinceItemMap.put(provinceRo.getPrefix(), provinceItemVos);
        }
        List<ProvincesItemResponseVo> itemVos = newArrayList();
        for (Map.Entry<String, List<ProvinceItemResponseVo>> entry : provinceItemMap.entrySet()) {
            ProvincesItemResponseVo itemVo = new ProvincesItemResponseVo();
            itemVo.setPrefix(entry.getKey());
            itemVo.setProvinces((entry.getValue()));
            itemVos.add(itemVo);
        }
        return itemVos;
    }

    public static String getFirstLetter(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(cl_chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                hanyupinyin += cl_chars[0];
            } else {// 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return hanyupinyin;
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
            return Lists.transform(provinces, new RegionToSimpleRegionVo());
        } catch (ExecutionException e) {
            return newArrayList();
        }
    }

    @Override
    public List<GeoTreeVo> findRegionByRegionLevel(Integer level) {
        return null;
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
                                    return regionRepository.findAll();
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
