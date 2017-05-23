package com.biz.soa.feign.service;

import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.exception.SoaException;

/**
 * Created by defei on 5/20/17.
 */
public abstract class AbstractFeignService {

	protected <T> T getResultData(MicroServiceResult<T> result) throws SoaException {

		if (result == null) {
			return null;
		} else if (result.isSuccess()) {
			return result.getData();
		} else {
			throw new SoaException(result.getException());
		}

	}
}
