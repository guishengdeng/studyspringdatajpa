package com.biz.gbck.vo.promotion;

import java.io.Serializable;

public class PromotionRespAppVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9067303786841273630L;

	private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否可使用优惠券
     */
    private Boolean voucherAble;

    /**
     * 是否互斥
     */
    private Boolean mutexAble;

    /**
     * 促销适用的商品Id
     */
    private String productIds;

    /**
     * 促销
     */
    private String promotionPolicyJson;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 是否启用
     */
    private Boolean enable = true;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getVoucherAble() {
		return voucherAble;
	}

	public void setVoucherAble(Boolean voucherAble) {
		this.voucherAble = voucherAble;
	}

	public Boolean getMutexAble() {
		return mutexAble;
	}

	public void setMutexAble(Boolean mutexAble) {
		this.mutexAble = mutexAble;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getPromotionPolicyJson() {
		return promotionPolicyJson;
	}

	public void setPromotionPolicyJson(String promotionPolicyJson) {
		this.promotionPolicyJson = promotionPolicyJson;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
    
}

    
