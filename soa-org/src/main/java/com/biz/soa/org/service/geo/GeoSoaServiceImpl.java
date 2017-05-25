package com.biz.soa.org.service.geo;

import com.biz.gbck.common.com.GeoStatus;
import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.dao.mysql.repository.geo.CityRepository;
import com.biz.gbck.dao.mysql.repository.geo.DistrictRepository;
import com.biz.gbck.dao.mysql.repository.geo.ProvinceRepository;
import com.biz.gbck.vo.geo.*;
import com.biz.service.AbstractBaseService;
import com.biz.soa.org.service.geo.interfaces.GeoSoaService;
import com.biz.soa.org.transformer.geo.RegionToSimpleRegionVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

/**
 * @author dylan
 */
@Service
public class GeoSoaServiceImpl extends AbstractBaseService implements GeoSoaService {


    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;



    @Override
    public List<SimpleRegionVo> findRegionByParentAreaLevelAndParentId(Integer areaLevel, Integer parentId) {
        List data=newArrayList();
        if(areaLevel == IArea.LEVEL_PROVINCE){
             data = cityRepository.findByProvinceIdAndStatus(parentId,GeoStatus.GEO_NORMAL.getValue());

        }else
        if(areaLevel == IArea.LEVEL_CITY){
             data = districtRepository.findByCityIdAndStatus(parentId,GeoStatus.GEO_NORMAL.getValue());
        }
        return Lists.transform(data, new RegionToSimpleRegionVo());
    }

    @Override
    public List<SimpleRegionVo> findRegionByLevel(Integer areaLevel) {
            List provinces =provinceRepository.findByStatus(GeoStatus.GEO_NORMAL.getValue());
            return Lists.transform(provinces, new RegionToSimpleRegionVo());
    }

}
