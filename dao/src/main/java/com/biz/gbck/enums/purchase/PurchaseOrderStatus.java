package com.biz.gbck.enums.purchase;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 采购单状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum PurchaseOrderStatus implements EnumerableValue {
	NEW(1, "新建"),
	AUDITING(1, "审批中"),
	REJECT(10, "否决"),
	CREATE_ORDER(15, "生成订单"),
	PACKAGE(20, "备货中"),
	DELIVERED(30, "已发货"),
	SIGNED_PART(40, "部分收货"),
	SIGNED_ALL(50, "已收货"),
	CANCEL(60,"取消");



	public static class Converter extends BaseEnumValueConverter<PurchaseOrderStatus> {}


	public final int value;

	public final String desc;

	PurchaseOrderStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public int getValue() {
		return value;
	}
}
