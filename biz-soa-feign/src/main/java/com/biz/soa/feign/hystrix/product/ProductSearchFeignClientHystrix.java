package com.biz.soa.feign.hystrix.product;

import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.search.ProductSearchResultEntityVo;
import com.biz.gbck.vo.search.ProductSearchResultVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.client.product.ProductSearchFeignClient;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/17 11:40.
 */
@Component
public class ProductSearchFeignClientHystrix implements ProductSearchFeignClient {
    @Override
    public MicroServiceResult<ProductSearchResultVo<ProductSearchResultEntityVo>> productSearch(ProductAppListReqVo reqVo) {
        return null;
    }
}
