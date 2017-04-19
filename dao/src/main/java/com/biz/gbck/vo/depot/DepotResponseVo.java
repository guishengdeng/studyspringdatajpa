package com.biz.gbck.vo.depot;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.depot.BusinessStatus;
import com.biz.gbck.enums.depot.DepotType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;

/**
 * @author zhangcheng
 * @date 2016/12/14
 * @reviewer
 * @see
 */
public class DepotResponseVo implements Serializable {

    /**
     * 门店id
     */
    private String id;

    /**
     * 中台门店编码
     */
    private String depotCode;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 门店区域编号
     */
    private String areaNo;

    /**
     * 门店详细地址
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
     * 门店类型
     */
    private DepotType depotType;

    /**
     * 门店营业类型
     */
    private BusinessStatus businessStatus;

    /**
     * 省ID
     */
    private Integer provinceId;

    /**
     * 省的百度名称
     */
    private String provinceBaiduName;

    /**
     * 市ID
     */
    private Integer cityId;

    /**
     * 市的百度名称
     */
    private String cityBaiduName;

    /**
     * 区ID
     */
    private Integer districtId;

    /**
     * 区的百度名称
     */
    private String districtBaiduName;

    /**
     * 省仓编码
     */
    private String warehouseCode;

    /**
     * 门店营业开始时间
     */
    private Time beginBusiness;

    /**
     * 门店营业结束时间
     */
    private Time endBusiness;

    /**
     * 门店可用状态 启用／作废
     */
    private CommonStatusEnum enableStatus;

    /**
     * 省仓门店id
     */
    private Long warehouseDepotId;

    public DepotResponseVo() {

    }

    public Long getWarehouseDepotId() {
        return warehouseDepotId;
    }

    public void setWarehouseDepotId(Long warehouseDepotId) {
        this.warehouseDepotId = warehouseDepotId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
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

    public DepotType getDepotType() {
        return depotType;
    }

    public void setDepotType(DepotType depotType) {
        this.depotType = depotType;
    }

    public BusinessStatus getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(BusinessStatus businessStatus) {
        this.businessStatus = businessStatus;
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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Time getBeginBusiness() {
        return beginBusiness;
    }

    public void setBeginBusiness(Time beginBusiness) {
        this.beginBusiness = beginBusiness;
    }

    public Time getEndBusiness() {
        return endBusiness;
    }

    public void setEndBusiness(Time endBusiness) {
        this.endBusiness = endBusiness;
    }

    public CommonStatusEnum getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(CommonStatusEnum enableStatus) {
        this.enableStatus = enableStatus;
    }
}
