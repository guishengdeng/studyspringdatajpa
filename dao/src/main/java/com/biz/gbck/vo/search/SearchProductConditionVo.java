package com.biz.gbck.vo.search;

import com.biz.gbck.common.Constant;
import com.biz.gbck.enums.search.ProductSearchSort;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品搜索条件 Vo
 *
 * @author david-liu
 * @date 2017年01月07日
 * @reviewer
 */
public class SearchProductConditionVo implements Serializable {

    private static final long serialVersionUID = -2654803892537897300L;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 搜索字段
     */
    private List<SearchFieldVo> fields;

    /**
     * 区域 ID
     */
    private Long geoId;

    /**
     * 门店编号
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 商品类型(A类商品/B类商品)
     */
    private Integer type;

    /**
     * 商品搜索排序(默认采用综合排序)
     */
    private ProductSearchSort sort = ProductSearchSort.DEFAULT_SORT;

    /**
     * 页数(默认: 0)
     */
    private Integer page = Constant.DEFAULT_PAGE;

    /**
     * 页大小(默认: 50)
     */
    private Integer pageSize = Constant.DEFAULT_PAGE_SIZE;

    /**
     * 用户会员等级(默认: 1)
     */
    private Integer userLevel = Constant.DEFAULT_USER_LEVEL;

    /**
     * 当前定位纬度
     */
    private BigDecimal latitude;

    /**
     * 当前定位经度
     */

    private BigDecimal longitude;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<SearchFieldVo> getFields() {
        return fields;
    }

    public void setFields(List<SearchFieldVo> fields) {
        this.fields = fields;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public ProductSearchSort getSort() {
        return sort;
    }

    public void setSort(ProductSearchSort sort) {
        this.sort = sort;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
