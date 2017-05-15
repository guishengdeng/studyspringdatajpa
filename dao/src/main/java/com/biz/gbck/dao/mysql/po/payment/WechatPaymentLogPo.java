package com.biz.gbck.dao.mysql.po.payment;

import com.biz.pay.wechat.lang.ResultCode;
import com.biz.pay.wechat.lang.TradeType;

import javax.persistence.*;
import java.sql.Timestamp;

@SuppressWarnings("serial")
@Entity
@Table(name = "payment_wechat_log")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class WechatPaymentLogPo extends PaymentLogPo {

	@Enumerated(EnumType.STRING)
	private ResultCode resultCode;

	@Column(length = 16)
	private String errCode;

	@Column(length = 128)
	private String errCodeDes;

	@Column(length = 128)
	private String openid;

	@Column(length = 16)
	@Enumerated(EnumType.STRING)
	private TradeType tradeType;

	@Column(length = 16)
	private String bankType;

	private Integer totalFee;

	@Column(length = 8)
	private String feeType;

	private Integer cashFee;

	@Column(length = 16)
	private String cashFeeType;

	private Integer couponFee;

	private Integer couponCount;

	@Column(length = 20)
	private String couponId;

	private String attach;

	private Timestamp timeEnd;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getCashFee() {
		return cashFee;
	}

	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public Timestamp getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}
}