package com.biz.gbck.vo.geo;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 导出Geo_Province信息的Vo
 *
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class ExportOrImportGeoProvinceVo extends AbstractExportOrImportGeoVo implements Serializable {

    private static final long serialVersionUID = 4797960482273375609L;

    private Integer status;

    private Integer region;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
