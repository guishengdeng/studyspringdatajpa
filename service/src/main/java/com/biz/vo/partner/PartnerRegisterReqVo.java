package com.biz.vo.partner;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by haibin.tang on 2017/5/8.
 */
public class PartnerRegisterReqVo implements Serializable {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 登录密码
     */
    @NotNull(message = "登录密码不能为空")
    private String password;

    /**
     * 公司名称
     */
    @NotNull(message = "公司名称不能为空")
    private String name;
    /**
     * 法人名字(联系人)
     */
    @NotNull(message = "法人名字不能为空")
    private String corporateName;

    /**
     * 法人联系电话
     */
    @NotNull(message = "法人联系电话不能为空")
    private String mobile;

    /**
     * 法人联系人2
     */
    private String contactName;

    /**
     * 联系电话2
     */
    private String mobile2;
    /**
     * 经营品类
     */
    @NotNull(message = "经营品类不能为空")
    private String category;

    /**
     * 注册资金
     */
    @NotNull(message = "注册资金不能为空")
    private String capital;

    /**
     * 经营规模
     */
    @NotNull(message = "经营规模不能为空")
    private String businessScale;

    /**
     * 意向销售区域
     */
    @NotNull(message = "意向销售区域不能为空")
    private String intentionSaleArea;

    /**
     * 目前销售区域
     */
    @NotNull(message = "目前销售区域不能为空")
    private String currentSaleArea;

    /**
     * 团队人数
     */
    @NotNull(message = "团队人数不能为空")
    private String teamSize;

    /**
     * 车辆数量
     */
    @NotNull(message = "车辆数量不能为空")
    private String vehicleSize;

    /**
     * 仓库面积
     */
    @NotNull(message = "仓库面积不能为空")
    private String storageSpace;

    /**
     * 客户数量
     */
    @NotNull(message = "客户数量不能为空")
    private String customerNumber;

    /**
     * 营业执照
     */
    @NotNull(message = "请上传营业执照")
    private String businessLicense;

    /**
     * 酒水通行证
     */
    @NotNull(message = "请上传酒水通行证")
    private String winePermit;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getBusinessScale() {
        return businessScale;
    }

    public void setBusinessScale(String businessScale) {
        this.businessScale = businessScale;
    }

    public String getIntentionSaleArea() {
        return intentionSaleArea;
    }

    public void setIntentionSaleArea(String intentionSaleArea) {
        this.intentionSaleArea = intentionSaleArea;
    }

    public String getCurrentSaleArea() {
        return currentSaleArea;
    }

    public void setCurrentSaleArea(String currentSaleArea) {
        this.currentSaleArea = currentSaleArea;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(String storageSpace) {
        this.storageSpace = storageSpace;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getWinePermit() {
        return winePermit;
    }

    public void setWinePermit(String winePermit) {
        this.winePermit = winePermit;
    }
}
