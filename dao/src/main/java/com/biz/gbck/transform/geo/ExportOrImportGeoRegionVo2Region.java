package com.biz.gbck.transform.geo;


import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.gbck.vo.geo.ExportOrImportGeoRegionVo;
import com.google.common.base.Function;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class ExportOrImportGeoRegionVo2Region implements Function<ExportOrImportGeoRegionVo, RegionPo> {

    @Override
    public RegionPo apply(ExportOrImportGeoRegionVo vo) {
        if (vo != null){
            RegionPo region = new RegionPo();
            try {
                BeanUtils.copyProperties(region , vo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return region;
        }
        return null;
    }
}
