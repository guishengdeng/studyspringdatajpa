package com.biz.gbck.vo.warehouse.event.vo;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 中台省仓更新事件Vo
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public class UpdateWarehouseEventVo implements Serializable{

    private static final long serialVersionUID = 7501675439709580845L;

    /**
     * ID
     */
    private Long id;

    /**
     * 仓库编号
     */
    private String warehouseCode;

    /**
     * 仓库名称描述
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
     * 市ID
     */
    private Integer cityId;

    /**
     * 区ID
     */
    private Integer districtId;

    /**
     * 仓库可用状态
     */
    private CommonStatusEnum warehouseStatus;

    /**
     * 省仓创建时间
     */
    private Timestamp createTime;

    /**
     * 省仓更新时间
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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

    public CommonStatusEnum getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(CommonStatusEnum warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }
}
