package com.biz.gbck.vo.org;

/**
 * Created by death on 2016/8/2.
 */
public class User20VIPVo {

    /**
     * 编号
     */
    String num;

    /**
     * 省区
     */
    String provinceName;

    /**
     * 合作者姓名
     */
    String partnerName;

    /**
     * 手机号
     */
    String mobile;

    /**
     * 收入门店编码
     */
    String depotId;

    /**
     * shopName
     */
    String shopName;

    /**
     * 收货 省
     */
    String receiveProvince;

    /**
     * 收货 市
     */
    String receiveCity;

    /**
     * 收货 区/县
     */
    String receiveCountry;

    /**
     * 固定收货地址
     */
    String toAddress;

    /**
     * 收货地址是否与收入门店区域匹配
     */
    String isMatch;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince;
    }

    public String getReceiveCity() {
        return receiveCity;
    }

    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity;
    }

    public String getReceiveCountry() {
        return receiveCountry;
    }

    public void setReceiveCountry(String receiveCountry) {
        this.receiveCountry = receiveCountry;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(String isMatch) {
        this.isMatch = isMatch;
    }
}
