package com.biz.gbck.vo.depot;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 门店搜索条件Vo
 *
 * @author Nian.Li <br>2016年9月5日
 */
public class SearchDepotConditionVo implements Serializable {

    private static final long serialVersionUID = -8340231011147691456L;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 排序  距离升序 distanceAsc
     */
    private String sort;

    /**
     * 省id
     */
    private Integer provinceId;

    /**
     * 经度
     */
    @NotNull(message = "经度不能为空")
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    /**
     * 多少公里以内的门店
     * 注：如果查询索引，该参数可以不传递
     */
    private Double distance = 7D;

    /**
     * 页数
     */
    private int page = 0;

    /**
     * 页大小
     */
    private int pageSize = 10;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

