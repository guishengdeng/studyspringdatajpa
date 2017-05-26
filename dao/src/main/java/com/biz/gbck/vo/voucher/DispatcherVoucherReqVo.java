package com.biz.gbck.vo.voucher;

import java.io.Serializable;
import java.util.List;

import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;


public class DispatcherVoucherReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -183450805590547978L;

	private List<Long> userIds;
	private VoucherTypeRo voucherTypeRo;
	private Integer dispatcherCnt;
	private String loginUsername;
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public VoucherTypeRo getVoucherTypeRo() {
		return voucherTypeRo;
	}
	public void setVoucherTypeRo(VoucherTypeRo voucherTypeRo) {
		this.voucherTypeRo = voucherTypeRo;
	}
	public Integer getDispatcherCnt() {
		return dispatcherCnt;
	}
	public void setDispatcherCnt(Integer dispatcherCnt) {
		this.dispatcherCnt = dispatcherCnt;
	}
	public String getLoginUsername() {
		return loginUsername;
	}
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
}
