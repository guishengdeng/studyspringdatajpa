package com.biz.gbck.dao.redis.ro.voucher;

public class VoucherWithType {

	public VoucherRo voucherRo;

	public VoucherTypeRo voucherTypeRo;

	private int VoucherQuantity;


	/**
	 * 冲抵金额
	 */
	public Integer offsetAmount=0;

	public VoucherWithType() {

	}

	public VoucherWithType(VoucherRo voucherRo, VoucherTypeRo voucherTypeRo) {
		super();
		this.voucherRo = voucherRo;
		this.voucherTypeRo = voucherTypeRo;
	}

	public VoucherRo getVoucherRo() {
		return voucherRo;
	}

	public VoucherTypeRo getVoucherTypeRo() {
		return voucherTypeRo;
	}

	public Integer getOffsetAmount() {
		return offsetAmount;
	}

	public void setOffsetAmount(Integer offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	public int getVoucherQuantity() {
		return VoucherQuantity;
	}

	public void setVoucherQuantity(int voucherQuantity) {
		VoucherQuantity = voucherQuantity;
	}
}
