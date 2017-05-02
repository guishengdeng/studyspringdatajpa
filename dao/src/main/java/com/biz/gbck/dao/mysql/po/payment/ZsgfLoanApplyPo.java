package com.biz.gbck.dao.mysql.po.payment;


import com.biz.gbck.dao.mysql.po.org.ShopPo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "zsgf_loan_apply")
public class ZsgfLoanApplyPo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 联系人姓名
     */
    @Column
    private String contactName;

    /**
     * 隔壁仓库注册手机号
     */
    @Column
    private String gbckRegisterMobile;

    /**
     * 完成商户名称
     */
    @Column
    private String finalShopName;

    /**
     * 店招名称(店铺招牌名称)
     */
    @Column
    private String shopAirName;

    /**
     * 店铺地址
     */
    @Column
    private String shopAddress;

    /**
     * 期望额度
     */
    @Column
    private Integer expectQuota;

    /**
     * 审批额度
     */
    @Column
    private Integer quota;

    /**
     * 优惠券总金额
     */
    @Column
    private Integer vouchersAmount;

    /**
     * 商户资质图片
     */
    @Column(columnDefinition = "TEXT")
    private String shopCertificateImgs;

    /**
     * 店铺
     */
    @ManyToOne
    @JoinColumn(name = "shopId", nullable = false)
    private ShopPo shop;

    /**
     * 审核状态
     */
    @Column(columnDefinition = "TINYINT", nullable = false)
    @Convert(converter = ZsgfApplyStatus.ZsgfApplyStatusConverter.class)
    private ZsgfApplyStatus applyStatus;

    /**
     * 审核备注
     */
    @Column
    private String auditComment;

    public ZsgfLoanApplyPo(String auditComment, String contactName, Integer expectQuota,
                           String finalShopName, String gbckRegisterMobile, Long id,
                           ZsgfApplyStatus paymentApplyStatus, ShopPo shop, String shopAddress, String shopAirName,
                           String shopCertificateImgs, Integer vouchersAmount, Integer quota) {
        this.auditComment = auditComment;
        this.contactName = contactName;
        this.expectQuota = expectQuota;
        this.finalShopName = finalShopName;
        this.gbckRegisterMobile = gbckRegisterMobile;
        this.id = id;
        this.applyStatus = paymentApplyStatus;
        this.shop = shop;
        this.shopAddress = shopAddress;
        this.shopAirName = shopAirName;
        this.shopCertificateImgs = shopCertificateImgs;
        this.vouchersAmount = vouchersAmount;
        this.quota = quota;
    }

    public ZsgfLoanApplyPo() {
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getExpectQuota() {
        return expectQuota;
    }

    public void setExpectQuota(Integer expectQuota) {
        this.expectQuota = expectQuota;
    }

    public String getFinalShopName() {
        return finalShopName;
    }

    public void setFinalShopName(String finalShopName) {
        this.finalShopName = finalShopName;
    }

    public String getGbckRegisterMobile() {
        return gbckRegisterMobile;
    }

    public void setGbckRegisterMobile(String gbckRegisterMobile) {
        this.gbckRegisterMobile = gbckRegisterMobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZsgfApplyStatus getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(ZsgfApplyStatus paymentApplyStatus) {
        this.applyStatus = paymentApplyStatus;
    }

    public ShopPo getShop() {
        return shop;
    }

    public void setShop(ShopPo shop) {
        this.shop = shop;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopAirName() {
        return shopAirName;
    }

    public void setShopAirName(String shopAirName) {
        this.shopAirName = shopAirName;
    }

    public Integer getVouchersAmount() {
        return vouchersAmount;
    }

    public void setVouchersAmount(Integer vouchersAmount) {
        this.vouchersAmount = vouchersAmount;
    }

    public String getShopCertificateImgs() {
        return shopCertificateImgs;
    }

    public void setShopCertificateImgs(String shopCertificateImgs) {
        this.shopCertificateImgs = shopCertificateImgs;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }
}
