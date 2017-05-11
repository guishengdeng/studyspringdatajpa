package com.biz.gbck.enums.order;


/**
 * 生成序列类型
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum SequenceType  {

	ORDER("order", "B2B", "", "订单编号"),

	PAYMENT("payment", "B2B", "", "支付编号"),

	PURCHASE("purchase", "AA", "", "采购单号"),

	RETURN("return", "AA", "", "退货单号"),

	STOCK_IN("stock_in", "", "", "入库单号"),

	STOCK_OUT("stock_out", "", "", "出库单号");

	private String name;

	private String prefix;

	private String suffix;

	private String description;

	SequenceType(String name, String prefix, String suffix, String description) {
		this.name = name;
		this.prefix = prefix;
		this.suffix = suffix;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
