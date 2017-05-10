package com.biz.service.geo;


import com.alibaba.fastjson.JSON;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.gbck.dao.mysql.repository.geo.CityRepository;
import com.biz.gbck.dao.mysql.repository.geo.DistrictRepository;
import com.biz.gbck.dao.mysql.repository.geo.ProvinceRepository;
import com.biz.gbck.dao.mysql.repository.geo.RegionRepository;
import com.biz.gbck.transform.geo.ExportOrImportGeoCityVo2City;
import com.biz.gbck.transform.geo.ExportOrImportGeoDistrictVo2District;
import com.biz.gbck.transform.geo.ExportOrImportGeoProvinceVo2Province;
import com.biz.gbck.transform.geo.ExportOrImportGeoRegionVo2Region;
import com.biz.gbck.vo.geo.ExportOrImportGeoCityVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoDistrictVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoProvinceVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoRegionVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.geo.interfaces.ImportGeoDataService;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
@Service
public class ImportGeoDataServiceImpl extends AbstractBaseService implements ImportGeoDataService {

    @Qualifier("regionRepository")
    @Autowired
    private RegionRepository regionRepository;

    @Qualifier("provinceRepository")
    @Autowired
    private ProvinceRepository provinceRepository;

    @Qualifier("cityRepository")
    @Autowired
    private CityRepository cityRepository;

    @Qualifier("districtRepository")
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    @Transactional
    public void importGeoRegion() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //首先获取到文件的绝对路径
        String path = ImportGeoDataServiceImpl.class.getClassLoader().getResource("importfile/geo_region_utf.txt").getPath();
        List<ExportOrImportGeoRegionVo> exportOrImportGeoRegionVos = Lists.newArrayList();
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            logger.debug("开始读取json文件");
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String str;
            while (StringUtils.isNotBlank(str = reader.readLine())) {
                exportOrImportGeoRegionVos.add(JSON.parseObject(str, ExportOrImportGeoRegionVo.class));
                if (logger.isDebugEnabled()) {
                    logger.debug("成功读取一行json并且反序列化为Vo ， Geo_Region为：{}", JSON.parseObject(str, ExportOrImportGeoRegionVo.class).toString());
                }
            }
        } catch (IOException e) {
            logger.warn("读入数据出错", e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {
            }
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
            } catch (IOException ignored) {
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
            }
        }
        List<RegionPo> regions = Lists.newArrayList();
        for (ExportOrImportGeoRegionVo vo : exportOrImportGeoRegionVos) {
            RegionPo region = new ExportOrImportGeoRegionVo2Region().apply(vo);
            regions.add(region);
        }
        logger.debug("Geo_Region数量为：{}", regions.size());
        regionRepository.save(regions);
        logger.debug("导入Geo_Region数据用时{}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    @Transactional
    public void importGeoProvince() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询出所有的Geo_Region信息
        List<RegionPo> regions = regionRepository.findAll();
        logger.debug("Geo_Region的数量为：{}", regions.size());
        //首先获取到文件的绝对路径
        String path = ImportGeoDataServiceImpl.class.getClassLoader().getResource("importfile/geo_province_utf.txt").getPath();
        List<ExportOrImportGeoProvinceVo> exportOrImportGeoProvinceVos = Lists.newArrayList();
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            logger.debug("开始读取json文件");
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String str;
            while (StringUtils.isNotBlank(str = reader.readLine())) {
                exportOrImportGeoProvinceVos.add(JSON.parseObject(str, ExportOrImportGeoProvinceVo.class));
            }
        } catch (IOException e) {
            logger.warn("读入数据出错", e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {
            }
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
            } catch (IOException ignored) {
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
            }
        }
        List<ProvincePo> provinces = Lists.newArrayList();
        for (ExportOrImportGeoProvinceVo vo : exportOrImportGeoProvinceVos) {
            ProvincePo province = new ExportOrImportGeoProvinceVo2Province().apply(vo);
            for (RegionPo region : regions) {
                if (Objects.equals(region.getId(), vo.getRegion())) {
                    province.setRegion(region);
                }
            }
            provinces.add(province);
        }
        logger.debug("Geo_Province的数量为：{}", provinces.size());
        provinceRepository.save(provinces);
        logger.debug("导入Geo_Province数据用时{}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    @Transactional
    public void importGeoCity() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询出所有的省信息
        List<ProvincePo> provinces = provinceRepository.findAll();
        logger.debug("所有省的数量为：{}", provinces.size());
        //首先获取到文件的绝对路径
        String path = ImportGeoDataServiceImpl.class.getClassLoader().getResource("importfile/geo_city_utf.txt").getPath();
        List<ExportOrImportGeoCityVo> exportOrImportGeoCityVos = Lists.newArrayList();
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            logger.debug("开始读取json文件");
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String str;
            while (StringUtils.isNotBlank(str = reader.readLine())) {
                exportOrImportGeoCityVos.add(JSON.parseObject(str, ExportOrImportGeoCityVo.class));
            }
        } catch (IOException e) {
            logger.warn("读入数据出错", e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {
            }
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
            } catch (IOException ignored) {
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
            }
        }
        List<CityPo> cities = Lists.newArrayList();
        for (ExportOrImportGeoCityVo vo : exportOrImportGeoCityVos) {
            CityPo city = new ExportOrImportGeoCityVo2City().apply(vo);
            for (ProvincePo province : provinces) {
                if (Objects.equals(province.getId(), vo.getProvince())) {
                    city.setProvince(province);
                }
            }
            cities.add(city);
        }
        logger.debug("Geo_City的数量为：{}", cities.size());
        cityRepository.save(cities);
        logger.debug("导入Geo_City数据用时{}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    @Transactional
    public void importGeoDistrict() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询出所有的城市信息
        List<CityPo> cities = cityRepository.findAll();
        logger.debug("城市数量为：{}", cities.size());
        //首先获取到文件的绝对路径
        String path = ImportGeoDataServiceImpl.class.getClassLoader().getResource("importfile/geo_district_utf.txt").getPath();
        List<ExportOrImportGeoDistrictVo> exportOrImportGeoDistrictVos = Lists.newArrayList();
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            logger.debug("开始读取json文件");
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String str;
            while (StringUtils.isNotBlank(str = reader.readLine())) {
                exportOrImportGeoDistrictVos.add(JSON.parseObject(str, ExportOrImportGeoDistrictVo.class));
            }
        } catch (IOException e) {
            logger.warn("读入数据出错", e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {
            }
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
            } catch (IOException ignored) {
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
            }
        }
        List<DistrictPo> districts = Lists.newArrayList();
        for (ExportOrImportGeoDistrictVo vo : exportOrImportGeoDistrictVos) {
            DistrictPo district = new ExportOrImportGeoDistrictVo2District().apply(vo);
            for (CityPo city : cities) {
                if (Objects.equals(city.getId(), vo.getCity())) {
                    district.setCity(city);
                    district.setProvinceId(city.getProvince().getId());
                }
            }
            districts.add(district);
        }
        logger.debug("所有区/县的数量为：{}", districts.size());
        districtRepository.save(districts);
        logger.debug("导入Geo_District数据用时{}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
