package com.biz.gbck.vo.common.response;

import com.google.common.base.Function;

public class AreaResponseVo2IdAndNameQueryVo implements Function<AreaResponseVo, IdAndNameQueryVo>{

	@Override
	public IdAndNameQueryVo apply(AreaResponseVo input) {
		IdAndNameQueryVo idAndName=new IdAndNameQueryVo();
		idAndName.setId(String.valueOf(input.getId()));
		idAndName.setName(input.getName());
		return idAndName;
	}
}
