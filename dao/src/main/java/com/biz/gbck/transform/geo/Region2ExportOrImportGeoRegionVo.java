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
public class Region2ExportOrImportGeoRegionVo implements Function<RegionPo, ExportOrImportGeoRegionVo> {

    @Override
    public ExportOrImportGeoRegionVo apply(RegionPo region) {
        if (region != null){
            ExportOrImportGeoRegionVo vo = new ExportOrImportGeoRegionVo();
            try {
                BeanUtils.copyProperties(vo , region);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return vo;
        }
        return null;
    }
}
