package com.biz.gbck.vo.voucher;

import java.io.Serializable;

import com.biz.gbck.dao.mysql.po.voucher.VoucherExpireType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherLimitType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypeStatus;

public class VoucherTypeVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 579340998634180031L;

	private Long id;

    private String name;

    private String faceValue;

    private String paymentLimit;

    private VoucherLimitType voucherLimitType;

    private Integer productType;

    private String productIds;

    private VoucherTypeStatus typeStatus;

    private String paymentType;

    private int fetchType;

    private VoucherExpireType voucherExpireType;

    private String startTime;

    private String expireTime;

    private String periodDays;

    private Integer issueCount;

    private String minCountInPool;

    private String mailTo;

    private String issuerName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public String getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(String paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public VoucherLimitType getVoucherLimitType() {
        return voucherLimitType;
    }

    public void setVoucherLimitType(VoucherLimitType voucherLimitType) {
        this.voucherLimitType = voucherLimitType;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public VoucherTypeStatus getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(VoucherTypeStatus typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getFetchType() {
        return fetchType;
    }

    public void setFetchType(int fetchType) {
        this.fetchType = fetchType;
    }

    public VoucherExpireType getVoucherExpireType() {
        return voucherExpireType;
    }

    public void setVoucherExpireType(VoucherExpireType voucherExpireType) {
        this.voucherExpireType = voucherExpireType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(String periodDays) {
        this.periodDays = periodDays;
    }

    public Integer getIssueCount() {
        return issueCount;
    }

    public void setIssueCount(Integer issueCount) {
        this.issueCount = issueCount;
    }

    public String getMinCountInPool() {
        return minCountInPool;
    }

    public void setMinCountInPool(String minCountInPool) {
        this.minCountInPool = minCountInPool;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
