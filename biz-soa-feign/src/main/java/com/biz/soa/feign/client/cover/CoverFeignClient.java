package com.biz.soa.feign.client.cover;

import com.biz.service.cover.CoverService;
import com.biz.soa.feign.hystrix.cover.CoverFeignClientHystrix;
import com.biz.soa.feign.hystrix.org.ShopFeignClientHystrix;
import com.biz.vo.cover.CoverReqVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: defei
 * @date 5/15/17
 */
@FeignClient(name = "depotnextdoor-mix-service", fallback = CoverFeignClientHystrix.class)
public interface CoverFeignClient extends CoverService {

	@Override
	@RequestMapping(value = "soa/cover/homepage", method = RequestMethod.POST)
	Object getHomePage(@RequestBody CoverReqVo reqVo);

}
