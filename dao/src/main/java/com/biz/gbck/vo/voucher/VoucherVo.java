package com.biz.gbck.vo.voucher;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypeStatus;

public class VoucherVo implements Comparable<VoucherVo> {

    private Long voucherTypeId;

    private Long voucherId;

    private String categoryInfo;

    private String name;

    private Integer faceValue;

    private Integer paymentLimit;

    /**
     * 支付类型
     * 多个支付方式采用逗号分割
     */
    private String payPatternLimit;

    private int categoryId;

    private Integer typeStatus;

    private int useStatus;

    private String freeMsg;

    public String getFreeMsg() {
        return freeMsg;
    }

    public void setFreeMsg(String freeMsg) {
        this.freeMsg = freeMsg;
    }

    public Integer getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(Integer paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public String getPayPatternLimit() {
        return payPatternLimit;
    }

    public void setPayPatternLimit(String payPatternLimit) {
        this.payPatternLimit = payPatternLimit;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(VoucherTypeStatus typeStatus) {
        this.typeStatus = typeStatus.getValue();
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    private Long startTime;

    private Long expireTime;

    public VoucherVo() {

    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override public int compareTo(VoucherVo o) {
        return new Long(this.expireTime - o.expireTime).intValue();
    }
}
