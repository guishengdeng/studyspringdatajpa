package com.biz.gbck.vo.org;

import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codelogger.utils.ArrayUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * APP(商品、订单、优惠、促销)用户相关vo
 *
 * @author lei
 * @date 2017年05月24日
 * @reviewer
 * @see
 */
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 4786928677281986988L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 是否是店长
     */
    private Boolean isAdmin = false;

    /**
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 绑定的店铺
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 法人名字
     */
    private String corporateName;

    /**
     * 店铺类型
     */
    private Long shopTypeId;

    /**
     * 店铺手机
     */
    private String shopMobile;

    /**
     * 店铺电话
     */
    private String tel;

    /**
     * 店铺经度
     */
    private BigDecimal longitude;

    /**
     * 店铺纬度
     */
    private BigDecimal latitude;

    /**
     * 收货人姓名
     */
    private String deliveryName;

    /**
     * 收货人电话
     */
    private String deliveryMobile;

    /**
     * 店铺收货地址
     */
    private String deliveryAddress;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 省
     */
    private Long provinceId;

    /**
     * 市
     */
    private Long cityId;

    /**
     * 区
     */
    private Long districtId;

    /**
     * 销售区域
     */
    private String saleAreas;

    /**
     * 邀请者
     */
    private String inviterCode;

    /**
     * 店铺状态
     */
    private Integer shopStatus;

    /**
     * 支持的 付款方式
     */
    private List<Long> supportPaymentIds;

    /**
     * 禁用的付款方式
     */
    private List<Long> disabledPaymentIds;


    //渠道
    private Integer channel;

    //渠道用户id
    private Long channelUserId;

    /**
     * Shop上级合伙人Id
     */
    private Long partnerId;

    /**
     * 平台公司Id
     */
    private Long platformId;

    /**
     * 客户组Id
     */
    private Long companyGroupId;

    public UserInfoVo() {
    }

    public UserInfoVo(UserRo userRo, ShopRo shopRo) {
        this();
        this.setUserId(userRo.getId());
        this.setAccount(userRo.getAccount());
        this.setName(userRo.getName());
        this.setMobile(userRo.getMobile());
        this.setAdmin(userRo.getAdmin());
        this.setUserStatus(userRo.getStatus());
        this.setShopId(userRo.getShopId());
        this.setShopName(shopRo.getName());
        this.setShopTypeId(shopRo.getShopTypeId());
        this.setShopMobile(shopRo.getMobile());
        this.setTel(shopRo.getTel());
        this.setShopAddress(shopRo.getShopAddress());
        this.setLatitude(shopRo.getLatitude());
        this.setLongitude(shopRo.getLongitude());
        this.setDeliveryName(shopRo.getDeliveryName());
        this.setDeliveryMobile(shopRo.getDeliveryMobile());
        this.setDeliveryAddress(shopRo.getDeliveryAddress());
        this.setProvinceId(shopRo.getProvinceId());
        this.setCityId(shopRo.getCityId());
        this.setDistrictId(shopRo.getDistrictId());
        this.setSaleAreas(shopRo.getSaleAreas());
        this.setShopStatus(shopRo.getStatus());
        List<Long> supportPaymentIds = ArrayUtils.toList(StringUtils.split(shopRo.getSupportPaymentIds(), ","))
                .stream().map(Long::valueOf).collect(Collectors.toList());
        this.setSupportPaymentIds(supportPaymentIds);
        List<Long> disabledPaymentIds = ArrayUtils.toList(StringUtils.split(shopRo.getDisabledPaymentIds(), ","))
                .stream().map(Long::valueOf).collect(Collectors.toList());
        this.setDisabledPaymentIds(disabledPaymentIds);
        this.setChannel(shopRo.getChannel());
        this.setChannelUserId(shopRo.getChannelUserId());
        this.setPartnerId(shopRo.getPartnerId());
        this.setPlatformId(userRo.getPlatformId());
        this.setCompanyGroupId(shopRo.getCompanyGroupId());

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getSaleAreas() {
        return saleAreas;
    }

    public void setSaleAreas(String saleAreas) {
        this.saleAreas = saleAreas;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public List<Long> getSupportPaymentIds() {
        return supportPaymentIds;
    }

    public void setSupportPaymentIds(List<Long> supportPaymentIds) {
        this.supportPaymentIds = supportPaymentIds;
    }

    public List<Long> getDisabledPaymentIds() {
        return disabledPaymentIds;
    }

    public void setDisabledPaymentIds(List<Long> disabledPaymentIds) {
        this.disabledPaymentIds = disabledPaymentIds;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Long getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(Long channelUserId) {
        this.channelUserId = channelUserId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(Long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }

    //收货人姓名(优先顺序: 收货人姓名>法人姓名>店铺名称)
    public String getUsableDeliveryName() {
        String usableDeliveryName;
        if (StringUtils.isNotEmpty(this.getDeliveryName())) {
            usableDeliveryName = this.getDeliveryName();
        } else {
            if (StringUtils.isNotEmpty(this.getCorporateName())) {
                usableDeliveryName = this.getCorporateName();
            } else {
                usableDeliveryName = this.getShopName();
            }
        }
        return deliveryName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
