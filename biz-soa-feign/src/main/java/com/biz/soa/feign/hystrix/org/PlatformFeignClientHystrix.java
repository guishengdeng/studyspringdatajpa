package com.biz.soa.feign.hystrix.org;


import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.feign.client.org.PlatformFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created dylan
 */
@Component
public class PlatformFeignClientHystrix implements PlatformFeignClient {


    @Override
    public PageVO<PlatformResSearchVo> findPlatformList(@RequestBody PlatformSearchVo vo) {
        return null;
    }

    @Override
    public PageVO<PartnerSearchResVo> findPartnerList(@RequestBody PartnerSearchVo vo) {
        return null;
    }

    @Override
    public PartnerSearchResVo findPartnerById(@RequestParam("id") Long id) {
        return null;
    }
}
