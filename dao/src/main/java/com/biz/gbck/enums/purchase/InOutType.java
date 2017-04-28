package com.biz.gbck.enums.purchase;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 出入库类型
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum InOutType implements EnumerableValue {
	OTHER(0, "其他"),
	SALE(1, "销售"),
	RETURN(2, "退货"),
	INVENTORY(3, "盘点"),
	FREE(4, "客情"),
	WELFARE(5, "福利");


	public static class Converter extends BaseEnumValueConverter<InOutType> {}


	public final int value;

	public final String desc;

	InOutType(int value, String desc) {
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
