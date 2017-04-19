package com.biz.gbck.vo.warehouse;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zhangcheng
 * @date 2016/12/20
 * @reviewer
 * @see
 */
public class WarehouseResponseVo implements Serializable {

    private static final long serialVersionUID = -4390525765849640880L;

    /**
     * ID
     */
    private Long id;

    /**
     * 省仓编号
     */
    private String warehouseCode;

    /**
     * 省仓名称描述
     */
    private String name;

    /**
     * 公司名称
     */
    private String companyName;


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
     * 仓库可用状态
     */
    private CommonStatusEnum commonStatus;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 中台门店编号
     */
    private String depotCode;

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCityBaiduName() {
        return cityBaiduName;
    }

    public void setCityBaiduName(String cityBaiduName) {
        this.cityBaiduName = cityBaiduName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getProvinceBaiduName() {
        return provinceBaiduName;
    }

    public void setProvinceBaiduName(String provinceBaiduName) {
        this.provinceBaiduName = provinceBaiduName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictBaiduName() {
        return districtBaiduName;
    }

    public void setDistrictBaiduName(String districtBaiduName) {
        this.districtBaiduName = districtBaiduName;
    }

    public CommonStatusEnum getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(CommonStatusEnum commonStatus) {
        this.commonStatus = commonStatus;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
