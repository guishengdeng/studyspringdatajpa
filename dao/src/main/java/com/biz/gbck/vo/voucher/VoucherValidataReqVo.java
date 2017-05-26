package com.biz.gbck.vo.voucher;

import java.io.Serializable;
import java.util.List;


public class VoucherValidataReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3138130336383682928L;

	private List<Long> userIds;
	private Long shopTypeId;
	private Long voucherTypeId;
	private int dispatcherCnt;
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public Long getShopTypeId() {
		return shopTypeId;
	}
	public void setShopTypeId(Long shopTypeId) {
		this.shopTypeId = shopTypeId;
	}
	public Long getVoucherTypeId() {
		return voucherTypeId;
	}
	public void setVoucherTypeId(Long voucherTypeId) {
		this.voucherTypeId = voucherTypeId;
	}
	public int getDispatcherCnt() {
		return dispatcherCnt;
	}
	public void setDispatcherCnt(int dispatcherCnt) {
		this.dispatcherCnt = dispatcherCnt;
	}
	
	
}
