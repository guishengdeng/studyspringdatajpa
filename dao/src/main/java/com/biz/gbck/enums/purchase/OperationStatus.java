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
public enum OperationStatus implements EnumerableValue {
	NEW(1, "新建"),
	IN_PROCESS(5, "处理中"),
	FINISH(10, "处理完成");

	public static class Converter extends BaseEnumValueConverter<OperationStatus> {}


	public final int value;

	public final String desc;

	OperationStatus(int value, String desc) {
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
