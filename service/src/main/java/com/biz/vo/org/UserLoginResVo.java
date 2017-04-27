package com.biz.vo.org;

import com.biz.gbck.dao.redis.ro.org.ShopRo;
import org.codelogger.utils.StringUtils;

import java.util.List;

/**
 * Created by defei on 3/11/16.
 */
public class UserLoginResVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商铺id
     */
    private Long shopId;

    /**
     * 商户名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否是店铺管理员
     */
    private Boolean isAdmin;

    /**
     * 送货地址
     */
    private String deliveryAddress;

    /**
     * 商户详情审核状态
     */
    private Integer detailAuditStatus;

    /**
     * 详情被拒原因
     */
    private List<String> detailRejectReasons;

    /**
     * 资质审核状态
     */
    private Integer qualificationAuditStatus;

    /**
     * 资质被拒原因
     */
    private List<String> qualificationRejectReasons;

    /**
     * 店铺状态
     */
    private Integer shopStatus;

    /**
     * 用户状态
     */
    private Integer userStatus;
    
    /**
     * 用户消息数
     */
    private Long msgCount;
    
    
    /**
     * 法人名字
     */
    private String corporateName;
    
    /**
     * 收货人姓名
     */
    private String deliveryName;

    /**
     * true 显示记我账上
     */
    private Boolean showPaymentButton = true;

    /**
     * 可用红包数量
     */
    private Integer luckMoneyCount = 0;

    /**
     * true 显示活动红点
     */
    private Boolean showActivityRedPoint = false;

    /**
     * true 未设置支付密码
     */
    private Boolean emptyPaymentPassword = true;

    public void setShopProperties(ShopRo shopRo) {
        setShopId(shopRo.getId());
        setDeliveryAddress(shopRo.getDeliveryAddress());
        setDetailAuditStatus(shopRo.getDetailAuditStatus());
        setQualificationAuditStatus(shopRo.getQualificationAuditStatus());
        setShopStatus(shopRo.getStatus());
        setName(shopRo.getName());
        setCorporateName(shopRo.getCorporateName());
        setDeliveryName(shopRo.getDeliveryName());
        this.emptyPaymentPassword = StringUtils.isBlank(shopRo.getPaymentPassword());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getDetailAuditStatus() {
        return detailAuditStatus;
    }

    public void setDetailAuditStatus(Integer detailAuditStatus) {
        this.detailAuditStatus = detailAuditStatus;
    }

    public Integer getQualificationAuditStatus() {
        return qualificationAuditStatus;
    }

    public void setQualificationAuditStatus(Integer qualificationAuditStatus) {
        this.qualificationAuditStatus = qualificationAuditStatus;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public List<String> getDetailRejectReasons() {
        return detailRejectReasons;
    }

    public void setDetailRejectReasons(List<String> detailRejectReasons) {
        this.detailRejectReasons = detailRejectReasons;
    }

    public List<String> getQualificationRejectReasons() {
        return qualificationRejectReasons;
    }

    public void setQualificationRejectReasons(List<String> qualificationRejectReasons) {
        this.qualificationRejectReasons = qualificationRejectReasons;
    }

	public Long getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Long msgCount) {
		this.msgCount = msgCount;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

    public Boolean getShowPaymentButton() {
        return showPaymentButton;
    }

    public void setShowPaymentButton(Boolean showPaymentButton) {
        this.showPaymentButton = showPaymentButton;
    }

    public Integer getLuckMoneyCount() {
        return luckMoneyCount;
    }

    public void setLuckMoneyCount(Integer luckMoneyCount) {
        this.luckMoneyCount = luckMoneyCount;
    }

    public Boolean getShowActivityRedPoint() {
        return showActivityRedPoint;
    }

    public void setShowActivityRedPoint(Boolean showActivityRedPoint) {
        this.showActivityRedPoint = showActivityRedPoint;
    }

    public Boolean getEmptyPaymentPassword() {
        return emptyPaymentPassword;
    }

}
