package com.biz.soa.hystrix;

import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.ProductFilterListItemVo;
import com.biz.gbck.vo.product.backend.ProductFilterListReqVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/10 23:19.
 */
@Component
public class ProductFeignClientHystrix implements ProductFeignClient {
    @Override
    public String getTestString() {
        return null;
    }

    @Override
    public MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVo>> getProductFilters(ProductFilterListReqVO reqVO) {
        MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVo>> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }
}
