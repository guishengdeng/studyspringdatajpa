package com.biz.gbck.dao.mysql.po.demo;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;

/**
 * Created by defei on 4/17/17.
 */
public enum SaleStatusEnum implements EnumerableNameAndValueAndDescription {

	FOR_SALE("待售", "等待出售", 1),
	LOCK("锁定", "已经被预订", 5),
	SOLD("已售", "已经卖了", 10);

	SaleStatusEnum(String name, String description, Integer value) {

		this.name = name;
		this.description = description;
		this.value = value;
	}

	private String name;

	private String description;

	private Integer value;

	public static class Converter extends BaseEnumNameAndValueAndDescriptionConverter<SaleStatusEnum> {

	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public int getValue() {

		return value;
	}

	@Override
	public String getDescription() {

		return description;
	}
}
