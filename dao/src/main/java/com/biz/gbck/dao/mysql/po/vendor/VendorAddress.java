package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.gbck.dao.mysql.po.product.Vendor;
import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.*;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
@Entity
@Table(name = "ven_vendor_address")
public class VendorAddress extends BaseEntity {

    private static final long serialVersionUID = -8216599954452708610L;

    // 是否默认退货
    private boolean isReceive;
    // 是否默认为发货
    private boolean isConsign;
    // 省 id
    private Integer province;

    //省名称
    @Column(length = 15)
    private String provinceName;
    // 城市 id
    private Integer city;

    //城市名称
    @Column(length = 15)
    private String cityName;

    // 区域 id
    private Integer district;

    //地区名称
    @Column(length = 15)
    private String districtName;

    // 详细地址
    @Column(length = 200)
    private String detailAddress;

    // 座机
    @Column(length = 15)
    private String telphone;

    // 手机
    @Column(length = 15)
    private String mobile;

    // 联系人
    @Column(length = 32)
    private String contacts;

    // 邮编
    @Column(length = 6)
    private String postCode;

    // 公司名
    @Column(length = 50)
    private String companyName;

    // 店铺
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // 备注
    @Column(length = 300)
    private String remark;

    //删除状态
    private Boolean deleteFlag = Boolean.FALSE;

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public boolean isReceive() {
        return isReceive;
    }

    public void setReceive(boolean isReceive) {
        this.isReceive = isReceive;
    }

    public boolean isConsign() {
        return isConsign;
    }

    public void setConsign(boolean isConsign) {
        this.isConsign = isConsign;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }


    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }


}
