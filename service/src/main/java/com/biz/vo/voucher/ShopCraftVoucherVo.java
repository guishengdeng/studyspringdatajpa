package com.biz.vo.voucher;

public class ShopCraftVoucherVo implements Comparable<ShopCraftVoucherVo> {

    private Long voucherTypeId;

    private String name;

    private Integer faceValue;

    private Integer paymentLimit;
    
    private String paymentType;

    private Integer maxCount;

    private String categoryInfo;

    private Integer remainAmount;

    private Long startTime;

    private Long expireTime;
    
    private Long categoryId;
    
    private String productIds;


    public ShopCraftVoucherVo() {
    }

    public Integer getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Integer remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Long getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
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
    public Integer getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(Integer paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
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

    
    public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override public int compareTo(ShopCraftVoucherVo o) {
        return this.getFaceValue() * this.getMaxCount() - this.getFaceValue() * this.getMaxCount();
    }
}
