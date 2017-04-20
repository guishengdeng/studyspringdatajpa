package com.biz.gbck.enums.order;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 订单状态
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum OrderStatus implements EnumerableValue {
	CREATED(10, "新创建"),
	ORDERED(20, "待发货"),
	DELIVERED(30, "已发货"),
	FINISHED(50, "已完成"),

	APPLY_REFUND(60,"申请退货"),
	REFUNDING(65,"退货中"),
	REFUND_REJECT(70,"退货审核不通过"),
	REFUNDED(80,"退款完成");



	public static class Converter extends BaseEnumValueConverter<OrderStatus> {}


	public final int value;

	public final String desc;

	OrderStatus(int value, String desc) {
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
