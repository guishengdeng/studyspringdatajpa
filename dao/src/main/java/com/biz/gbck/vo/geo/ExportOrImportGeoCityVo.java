package com.biz.gbck.vo.geo;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 导出Geo_City信息Vo
 *
 * @author zhangcheng
 * @date 2017/3/7
 * @reviewer
 * @see
 */
public class ExportOrImportGeoCityVo extends AbstractExportOrImportGeoVo implements Serializable {

    private static final long serialVersionUID = 3592284426681395639L;

    private Integer status;

    private Integer province;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
