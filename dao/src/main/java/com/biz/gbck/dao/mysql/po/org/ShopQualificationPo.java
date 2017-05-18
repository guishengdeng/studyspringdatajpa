package com.biz.gbck.dao.mysql.po.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by defei on 3/18/16.
 */
@Entity @Table(name = "shop_qualification") public class ShopQualificationPo implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;

    /**
     * 对应店铺
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "shopId") private ShopPo shop;

    /**
     * 营业执照ID
     */
    @Column(length = 50) private String businessLicenceId;

    /**
     * 营业执照名称
     */
    @Column(length = 50) private String businessLicenceName;

    /**
     * 营业执照
     */
    @Column(length = 300) private String businessLicence;

    /**
     * 门头照片
     */
    @Column(length = 300) private String shopPhoto;

    /**
     * 酒类流通许可证ID
     */
    @Column(length = 50) private String liquorSellLicenceId;

    /**
     * 酒类流通许可证
     */
    @Column(length = 300) private String liquorSellLicence;

    /**
     * 法人身份证Id
     */
    @Column(length = 50) private String corporateId;

    /**
     * 法人身份证
     */
    @Column(length = 300) private String corporateIdPhoto;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 审核时间
     */
    private Timestamp handTime;

    @Column(length = 50) private String handlerUserName;

    /**
     * 被拒原因
     */
    private String rejectReason;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopPo getShop() {
        return shop;
    }

    public void setShop(ShopPo shop) {
        this.shop = shop;
    }

    public String getBusinessLicenceId() {
        return businessLicenceId;
    }

    public void setBusinessLicenceId(String businessLicenceId) {
        this.businessLicenceId = businessLicenceId;
    }

    public String getBusinessLicenceName() {
        return businessLicenceName;
    }

    public void setBusinessLicenceName(String businessLicenceName) {
        this.businessLicenceName = businessLicenceName;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getLiquorSellLicenceId() {
        return liquorSellLicenceId;
    }

    public void setLiquorSellLicenceId(String liquorSellLicenceId) {
        this.liquorSellLicenceId = liquorSellLicenceId;
    }

    public String getLiquorSellLicence() {
        return liquorSellLicence;
    }

    public void setLiquorSellLicence(String liquorSellLicence) {
        this.liquorSellLicence = liquorSellLicence;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getCorporateIdPhoto() {
        return corporateIdPhoto;
    }

    public void setCorporateIdPhoto(String corporateIdPhoto) {
        this.corporateIdPhoto = corporateIdPhoto;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getHandTime() {
        return handTime;
    }

    public void setHandTime(Timestamp handTime) {
        this.handTime = handTime;
    }


    public String getHandlerUserName() {
		return handlerUserName;
	}

	public void setHandlerUserName(String handlerUserName) {
		this.handlerUserName = handlerUserName;
	}

	public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
