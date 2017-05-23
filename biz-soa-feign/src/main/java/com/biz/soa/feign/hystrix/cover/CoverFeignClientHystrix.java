package com.biz.soa.feign.hystrix.cover;

import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.client.cover.CoverFeignClient;
import com.biz.vo.cover.CoverReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;

/**
 * Created by defei on 2017/05/12 12:16.
 */
@Component
public class CoverFeignClientHystrix implements CoverFeignClient {

	private Logger logger = LoggerFactory.getLogger(CoverFeignClientHystrix.class);

	@PostConstruct
	public void setup(){

		logger.info("Init " + CoverFeignClientHystrix.class);
	}

	@Override
	public MicroServiceResult getHomePage(@RequestBody CoverReqVO reqVo) {

		logger.debug("Called me...");
		return MicroServiceResult.buildSuccess("{}");
	}

}
