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
	PRE_PAY(20, "待支付"),
	ORDERED(30, "待发货"),
	DELIVERED(40, "已发货"),
	FINISHED(50, "已完成"),

	APPLY_RETURN(60,"申请退货"),
	RETURNING(65,"退货中"),
	RETURN_REJECT(70,"退货审核不通过"),
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
