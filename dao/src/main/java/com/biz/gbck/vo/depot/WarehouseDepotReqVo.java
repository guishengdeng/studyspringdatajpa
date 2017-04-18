package com.biz.gbck.vo.depot;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.gbck.enums.depot.BusinessStatus;
import com.biz.gbck.enums.depot.DepotType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;

/**
 * 与省仓门店业务相关的请求Vo
 *
 * @author zhangcheng
 * @date 2017/1/13
 * @reviewer
 * @see
 */
public class WarehouseDepotReqVo implements Serializable {

    private static final long serialVersionUID = 758884212124935835L;

    /**
     * 门店id
     */
    private Long id;

    /**
     * 中台门店编码
     */
    private String depotCode;

    /**
     * 门店区域编号
     */
    private String areaNo;

    /**
     * 门店名称
     */
    private String name;

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
     * 省ID
     */
    private Integer provinceId;

    /**
     * 省百度名称
     */
    private String provinceBadiduName;

    /**
     * 市ID
     */
    private Integer cityId;

    /**
     * 市百度名称
     */
    private String cityBaiduName;

    /**
     * 区ID
     */
    private Integer districtId;

    /**
     * 区百度名称
     */
    private String districtBaiduName;

    /**
     * 门店类型
     */
    private DepotType depotType;

    /**
     * 门店营业类型
     */
    private BusinessStatus businessStatus;

    /**
     * 省仓编码
     */
    private String warehouseCode;

    /**
     * 门店可用状态
     */
    private CommonStatusEnum commonStatus;

    /**
     * 省仓门店id
     */
    private Long warehouseDepotId;

    /**
     * 门店营业开始时间
     */
    private Time beginBusiness;

    /**
     * 门店营业结束时间
     */
    private Time endBusiness;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProvinceBadiduName() {
        return provinceBadiduName;
    }

    public void setProvinceBadiduName(String provinceBadiduName) {
        this.provinceBadiduName = provinceBadiduName;
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

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public CommonStatusEnum getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(CommonStatusEnum commonStatus) {
        this.commonStatus = commonStatus;
    }

    public Long getWarehouseDepotId() {
        return warehouseDepotId;
    }

    public void setWarehouseDepotId(Long warehouseDepotId) {
        this.warehouseDepotId = warehouseDepotId;
    }
}
