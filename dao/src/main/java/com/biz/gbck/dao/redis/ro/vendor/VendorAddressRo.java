package com.biz.gbck.dao.redis.ro.vendor;

import com.biz.core.util.DateUtil;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.sql.Timestamp;

/**
 * @author mounan
 * @Description: 店铺地址Ro
 * @time:2017年1月4日 下午2:41:19
 */
@Ro(key = "ro:VendorAddressRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class VendorAddressRo extends BaseRedisObject<Long> implements Comparable<VendorAddressRo> {

    // 是否默认退货
    private boolean isReceive;

    // 是否默认为发货
    private boolean isConsign;

    // 省 id
    private Integer province;

    // 城市 id
    private Integer city;

    // 区域 id
    private Integer district;

    // 详细地址
    private String detailAddress;

    // 座机
    private String telphone;

    // 手机
    private String mobile;

    // 联系人
    private String contacts;

    // 邮编
    private String postCode;

    // 公司名
    private String companyName;

    // 店铺
    private Long vendorId;

    // 备注
    private String remark;

    private Boolean deleteFlag = Boolean.FALSE;

    private Timestamp createTimestamp = DateUtil.now();

    @Override
    public int compareTo(VendorAddressRo o) {
        return -this.createTimestamp.compareTo(o.getCreateTimestamp());
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

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
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

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

}
