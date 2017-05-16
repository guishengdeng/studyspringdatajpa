package com.biz.gbck.common.com;


import com.biz.gbck.common.exception.CommonRuntimeException;

import java.util.Objects;

/**
 * geo地区信息
 */
public enum GeoStatus {
    GEO_NORMAL("正常", 10),
    GEO_DISABLE("删除", 0);
    private final String description;

    private final Integer value;

    GeoStatus(String description, Integer value) {
        this.description = description;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }


    public static GeoStatus valueOf(Integer value) {
        for (GeoStatus geoStatus : values()) {
            if (Objects.equals(geoStatus.getValue(), value)) {
                return geoStatus;
            }
        }
        throw new CommonRuntimeException("Illegal geoStatus");
    }

}
