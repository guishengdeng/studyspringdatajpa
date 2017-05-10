package com.biz.service.geo;


import com.alibaba.fastjson.JSONObject;
import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.gbck.dao.mysql.repository.geo.CityRepository;
import com.biz.gbck.dao.mysql.repository.geo.DistrictRepository;
import com.biz.gbck.dao.mysql.repository.geo.ProvinceRepository;
import com.biz.gbck.dao.mysql.repository.geo.RegionRepository;
import com.biz.gbck.transform.geo.City2ExportOrImportGeoCityVo;
import com.biz.gbck.transform.geo.District2ExportOrImportGeoDistrictVo;
import com.biz.gbck.transform.geo.Province2ExportOrImportGeoProvinceVo;
import com.biz.gbck.transform.geo.Region2ExportOrImportGeoRegionVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoCityVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoDistrictVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoProvinceVo;
import com.biz.gbck.vo.geo.ExportOrImportGeoRegionVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.geo.interfaces.ExportGeoDataService;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 导出Geo服务
 *
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
@Service
public class ExportGeoDataServiceImpl extends AbstractBaseService implements ExportGeoDataService {

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
    public void exportGeoRegion() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询所有的Geo_region信息
        List<RegionPo> regions = regionRepository.findAll();
        logger.debug("所有的Geo_Region信息数量为：{}", regions.size());
        List<ExportOrImportGeoRegionVo> vos = Lists.newArrayList();
        for (RegionPo region : regions) {
            ExportOrImportGeoRegionVo vo = new Region2ExportOrImportGeoRegionVo().apply(region);
            vos.add(vo);
        }
        List<JSONObject> jsonObjects = Lists.newArrayList();
        File file = new File("C:\\Users\\dell\\Desktop\\export_geo_region.txt");
        for (ExportOrImportGeoRegionVo vo : vos) {
            String content = JsonUtil.obj2Json(vo);
            logger.debug("序列化Vo为content {}", content);
            JSONObject jsonObject = JSONObject.parseObject(content);
            jsonObjects.add(jsonObject);
        }
        this.exportDataToFile(jsonObjects, file);
        logger.debug("耗时：{}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    public void exportGeoProvince() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询出所有的Geo_Province信息
        List<ProvincePo> provinces = provinceRepository.findAll();
        logger.debug("所有Geo_Province信息数量为：{}", provinces.size());
        List<ExportOrImportGeoProvinceVo> vos = Lists.newArrayList();
        for (ProvincePo province : provinces) {
            ExportOrImportGeoProvinceVo vo = new Province2ExportOrImportGeoProvinceVo().apply(province);
            vos.add(vo);
        }
        List<JSONObject> jsonObjects = Lists.newArrayList();
        File file = new File("C:\\Users\\dell\\Desktop\\export_geo_province.txt");
        for (ExportOrImportGeoProvinceVo vo : vos) {
            String content = JsonUtil.obj2Json(vo);
            logger.debug("序列化Vo为content {}", content);
            JSONObject jsonObject = JSONObject.parseObject(content);
            jsonObjects.add(jsonObject);
        }
        this.exportDataToFile(jsonObjects, file);
        logger.debug("耗时：{}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    public void exportGeoCity() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询出所有的Geo_City信息
        List<CityPo> cities = cityRepository.findAll();
        logger.debug("所有Geo_City信息的数量为：{}", cities.size());
        List<ExportOrImportGeoCityVo> vos = Lists.newArrayList();
        for (CityPo city : cities) {
            ExportOrImportGeoCityVo vo = new City2ExportOrImportGeoCityVo().apply(city);
            vos.add(vo);
        }
        List<JSONObject> jsonObjects = Lists.newArrayList();
        File file = new File("C:\\Users\\dell\\Desktop\\export_geo_city.txt");
        for (ExportOrImportGeoCityVo vo : vos) {
            String content = JsonUtil.obj2Json(vo);
            logger.debug("序列化Vo为content {}", content);
            JSONObject jsonObject = JSONObject.parseObject(content);
            jsonObjects.add(jsonObject);
        }
        this.exportDataToFile(jsonObjects, file);
        logger.debug("耗时：{}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    public void exportGeoDistrict() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //查询出所有的Geo_District信息
        List<DistrictPo> districts = districtRepository.findAll();
        logger.debug("所有Geo_Districts信息的数量为：{}", districts.size());
        List<ExportOrImportGeoDistrictVo> vos = Lists.newArrayList();
        for (DistrictPo district : districts) {
            ExportOrImportGeoDistrictVo vo = new District2ExportOrImportGeoDistrictVo().apply(district);
            vos.add(vo);
        }
        List<JSONObject> jsonObjects = Lists.newArrayList();
        File file = new File("C:\\Users\\dell\\Desktop\\export_geo_district.txt");
        for (ExportOrImportGeoDistrictVo vo : vos) {
            String content = JsonUtil.obj2Json(vo);
            logger.debug("序列化Vo为content {}", content);
            JSONObject jsonObject = JSONObject.parseObject(content);
            jsonObjects.add(jsonObject);
        }
        this.exportDataToFile(jsonObjects, file);
        logger.debug("耗时：{}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    /**
     * 将需要出的数据写入文件
     *
     * @param jsonObjects
     * @param file
     */
    private void exportDataToFile(List<JSONObject> jsonObjects, File file) {
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            for (JSONObject jsonObject : jsonObjects) {
                pw.println(jsonObject);
                pw.flush();
            }
        } catch (IOException e) {
            logger.debug("写入数据出错", e);
        } finally {
            if (pw != null)
                pw.close();
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ignored) {
            }
        }
    }
}
