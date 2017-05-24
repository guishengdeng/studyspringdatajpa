package com.biz.soa.feign.client.org;

import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.feign.hystrix.org.PlatformFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 商户类型
 * @author: dylan
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = PlatformFeignClientHystrix.class)
public interface PlatformFeignClient {

    /**
     *查询平台公司
     */
    @RequestMapping(value = "soa/platform/findPlatformList", method = RequestMethod.POST)
    PageVO<PlatformResSearchVo> findPlatformList(@RequestBody PlatformSearchVo vo);

    /**
     *根据平台公司查询合伙人
     */
    @RequestMapping(value = "soa/platform/findPartnerList", method = RequestMethod.POST)
    PageVO<PartnerSearchResVo> findPartnerList(@RequestBody PartnerSearchVo vo);

    /**
     *查询平台公司
     */
    @RequestMapping(value = "soa/platform/findPartnerById", method = RequestMethod.POST)
    PartnerSearchResVo findPartnerById(@RequestParam("id") Long id);




}
