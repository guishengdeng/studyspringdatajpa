package com.biz.soa.feign.client.product;

import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.hystrix.product.ProductSearchFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by david-liu on 2017/05/16 00:13.
 */
@FeignClient(name = "depotnextdoor-search", fallback = ProductSearchFeignClientHystrix.class)
public interface ProductSearchFeignClient {
    @RequestMapping(value = "/search/product", method = RequestMethod.POST)
    MicroServiceResult<ProductSearchResultVo<ProductSearchResultEntityVo>> productSearch(@RequestBody ProductAppListReqVo reqVo);
}
