package com.biz.gbck.vo.common.response;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class ProvinceCitiesItemResponseVo implements Serializable {

    private String prefix;

    private List<CityItemResponseVo> cities;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<CityItemResponseVo> getCities() {
        return cities;
    }

    public void setCities(List<CityItemResponseVo> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
