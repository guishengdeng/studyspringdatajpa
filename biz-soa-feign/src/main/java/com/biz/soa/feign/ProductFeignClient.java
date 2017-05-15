package com.biz.soa.feign;

import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.ProductFilterListItemVo;
import com.biz.gbck.vo.product.backend.ProductFilterListReqVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.hystrix.ProductFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by david-liu on 2017/05/04 14:30.
 */
@FeignClient(name = "depotnextdoor-product", fallback = ProductFeignClientHystrix.class)
public interface ProductFeignClient {

    @RequestMapping(value = "/soa/product/test", method = RequestMethod.GET)
    String getTestString();

    @RequestMapping(value = "/soa/product/filter/list", method = RequestMethod.POST)
    MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVo>> getProductFilters(ProductFilterListReqVO reqVO);

}
