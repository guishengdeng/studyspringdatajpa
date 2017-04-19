package com.biz.gbck.vo.depot;

import com.biz.gbck.enums.depot.BusinessStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 同步门店数据Vo
 *
 * @author zhangcheng
 * @date 2017/2/13
 * @reviewer
 * @see
 */
public class SyncDepotDataVo implements Serializable {

    private static final long serialVersionUID = -8613615222181523438L;

    /**
     * 中台门店编码
     */
    private String depotCode;

    /**
     * 门店区域编码
     */
    private String areaNo;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 门店经度
     */
    private BigDecimal depotLongitude;

    /**
     * 门店纬度
     */
    private BigDecimal depotLatitude;

    /**
     * 省id
     */
    private Integer provinceId;

    /**
     * 省百度名称
     */
    private String provinceBaiduName;

    /**
     * 市id
     */
    private Integer cityId;

    /**
     * 市百度名称
     */
    private String cityBaiduName;

    /**
     * 区id
     */
    private Integer districtId;

    /**
     * 区百度名称
     */
    private String districtBaiduName;

    /**
     * 门店类型
     */
    private String depotType;

    /**
     * 门店营业类型
     */
    private BusinessStatus businessStatus;

    /**
     * 省仓编码
     */
    private String warehouseCode;

    /**
     * 门店营业开始时间
     */
    private String beginBusiness;

    /**
     * 门店营业结束时间
     */
    private String endBusiness;

    /**
     * 门店可用状态 启用／作废（这里需要门店可用转态的value值“1”或者“2”）
     */
    private Integer enableStatusValue;

    /**
     * 该门店所对应的省仓门店编码
     */
    private String warehouseDepotCode;

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getDepotLongitude() {
        return depotLongitude;
    }

    public void setDepotLongitude(BigDecimal depotLongitude) {
        this.depotLongitude = depotLongitude;
    }

    public BigDecimal getDepotLatitude() {
        return depotLatitude;
    }

    public void setDepotLatitude(BigDecimal depotLatitude) {
        this.depotLatitude = depotLatitude;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceBaiduName() {
        return provinceBaiduName;
    }

    public void setProvinceBaiduName(String provinceBaiduName) {
        this.provinceBaiduName = provinceBaiduName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityBaiduName() {
        return cityBaiduName;
    }

    public void setCityBaiduName(String cityBaiduName) {
        this.cityBaiduName = cityBaiduName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictBaiduName() {
        return districtBaiduName;
    }

    public void setDistrictBaiduName(String districtBaiduName) {
        this.districtBaiduName = districtBaiduName;
    }

    public String getDepotType() {
        return depotType;
    }

    public void setDepotType(String depotType) {
        this.depotType = depotType;
    }

    public BusinessStatus getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(BusinessStatus businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getBeginBusiness() {
        return beginBusiness;
    }

    public void setBeginBusiness(String beginBusiness) {
        this.beginBusiness = beginBusiness;
    }

    public String getEndBusiness() {
        return endBusiness;
    }

    public void setEndBusiness(String endBusiness) {
        this.endBusiness = endBusiness;
    }

    public Integer getEnableStatusValue() {
        return enableStatusValue;
    }

    public void setEnableStatusValue(Integer enableStatusValue) {
        this.enableStatusValue = enableStatusValue;
    }

    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
