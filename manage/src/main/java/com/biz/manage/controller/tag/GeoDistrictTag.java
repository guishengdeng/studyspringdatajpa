package com.biz.manage.controller.tag;


import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.vo.geo.SimpleRegionVo;
import com.biz.soa.feign.client.global.GeoFeignClient;
import com.biz.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import static java.lang.String.format;

/**
 * Created by defei on 4/1/16.
 */
public class GeoDistrictTag extends TagSupport {

    private static final long serialVersionUID = -2123253186591293149L;

    private Integer cityId;

    private Integer districtId;

    @Override public int doStartTag() throws JspException {

        GeoFeignClient geoFeignClient = SpringContextUtil.getBean(GeoFeignClient.class);
        List<SimpleRegionVo> cities =
                geoFeignClient.findRegionByParentAreaLevelAndParentId(IArea.LEVEL_CITY, cityId);
        JspWriter out = pageContext.getOut();
        try {
            for (SimpleRegionVo data : cities) {
                if (Objects.equals(districtId, data.getId())) {
                    out.print(format("<option value=\"%s\" selected >%s</option>", data.getId(),
                        data.getName()));
                } else {
                    out.print(
                        format("<option value=\"%s\" >%s</option>", data.getId(), data.getName()));
                }
            }
            return SKIP_BODY;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}