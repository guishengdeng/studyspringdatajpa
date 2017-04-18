package com.biz.gbck.vo.depot;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.gbck.enums.depot.BusinessStatus;
import com.biz.gbck.enums.depot.DepotType;
import com.biz.gbck.vo.PageableRequestVo;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangcheng
 * @date 2016/12/12
 * @reviewer
 * @see
 */
public class DepotQueryReqVo extends PageableRequestVo implements Serializable {

    /**
     * 门店id
     */
    private String id;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 中台门店编号
     */
    private String depotCode;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店经度
     */
    private String depotLongitude;

    /**
     * 门店纬度
     */
    private String depotLatitude;

    /**
     * 门店区域编号
     */
    private String areaNo;

    /**
     * 门店类型
     */
    private DepotType depotType;

    /**
     * 门店营业类型
     */
    private BusinessStatus businessStatus;

    /**
     * 门店可用状态 启用／作废
     */
    private CommonStatusEnum enableStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return StringUtils.trim(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepotLongitude() {
        return depotLongitude;
    }

    public void setDepotLongitude(String depotLongitude) {
        this.depotLongitude = depotLongitude;
    }

    public String getDepotLatitude() {
        return depotLatitude;
    }

    public void setDepotLatitude(String depotLatitude) {
        this.depotLatitude = depotLatitude;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public CommonStatusEnum getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(CommonStatusEnum enableStatus) {
        this.enableStatus = enableStatus;
    }
}
