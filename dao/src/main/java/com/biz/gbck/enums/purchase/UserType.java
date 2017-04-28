package com.biz.gbck.enums.purchase;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 用户类型(//TODO 暂定 以刘滨那边为准)
 *
 * @author lei
 * @date 2017年04月28日
 * @reviewer
 * @see
 */
public enum UserType implements EnumerableValue {
	SAP(1, "SAP"),
	PLATFORM(2, "平台"),
	COMPANY(3, "省公司"),
	PARTNER(4, "合伙人"),
	TERMINAL(5, "终端用户");

	public static class Converter extends BaseEnumValueConverter<UserType> {}


	public final int value;

	public final String desc;

	UserType(int value, String desc) {
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
