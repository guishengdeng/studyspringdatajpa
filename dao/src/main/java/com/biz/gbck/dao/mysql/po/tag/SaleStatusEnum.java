package com.biz.gbck.dao.mysql.po.tag;

import com.biz.core.enums.EnumerableNameAndValueAndDescription;
import com.biz.core.enums.converter.BaseEnumNameAndValueAndDescriptionConverter;

/**
 * Created by lzz on 5/4/17.
 */
public enum SaleStatusEnum implements EnumerableNameAndValueAndDescription {

	ENABLE("启用", "启用", 1),
	DISABLE("禁用", "禁止使用", 0);

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
