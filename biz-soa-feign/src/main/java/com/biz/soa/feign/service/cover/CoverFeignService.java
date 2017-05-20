package com.biz.soa.feign.service.cover;

import com.biz.service.cover.CoverService;
import com.biz.soa.feign.client.cover.CoverFeignClient;
import com.biz.soa.feign.service.AbstractFeignService;
import com.biz.vo.cover.CoverReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by defei on 5/20/17.
 */
@Service
public class CoverFeignService extends AbstractFeignService implements CoverService {

	@Autowired
	private CoverFeignClient coverFeignClient;

	@Override
	public Object getHomePage(CoverReqVO reqVo) {

		return getResultData(coverFeignClient.getHomePage(reqVo));
	}
}
