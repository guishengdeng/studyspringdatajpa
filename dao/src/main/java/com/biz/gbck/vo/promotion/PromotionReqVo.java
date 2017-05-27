package com.biz.gbck.vo.promotion;

import java.io.Serializable;
import java.util.List;

public class PromotionReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7762102674412229303L;

	/**
	 * 商品id
	 */
	private Long productIds;
	
	 /**
     * 分类
     */
    private Long categoryId;
	/**
	 * 用户组ids
	 */
	private Long companyGroupId;
	public Long getProductIds() {
		return productIds;
	}
	public void setProductIds(Long productIds) {
		this.productIds = productIds;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getCompanyGroupId() {
		return companyGroupId;
	}
	public void setCompanyGroupId(Long companyGroupId) {
		this.companyGroupId = companyGroupId;
	}
}
