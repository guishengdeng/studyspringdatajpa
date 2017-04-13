package com.biz.redis.trans.impl.convertor;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public abstract class AbstractNumberConvertor<N extends Number> extends AbstractSimpleValueConverter<N> {

	@Override
	protected String val2String(N val) {
		return val.toString();
	}
}
