package com.biz.soa.feign.hystrix.product;

import com.biz.gbck.vo.product.ProductFilterListItemVO;
import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.ProductFilterListReqVO;
import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.search.IncrProductIdxReqVo;
import com.biz.gbck.vo.search.ProductIdxVO;
import com.biz.gbck.vo.search.TotalProductIdxReqVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.client.product.ProductFeignClient;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/10 23:19.
 */
@Component
public class ProductFeignClientHystrix implements ProductFeignClient {

    @Override
    public MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVO>> getProductFilters(ProductFilterListReqVO reqVO) {
        MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVO>> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }

    @Override
    public MicroServiceResult<List<ProductFilterListItemVO>> getProductSearchResultFilters(Long categoryId) {
        MicroServiceResult<List<ProductFilterListItemVO>> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }

    @Override
    public MicroServiceResult<ProductAppListRespVO> getProductSearchResult(ProductAppListReqVo reqVo) {
        MicroServiceResult<ProductAppListRespVO> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }

    @Override
    public MicroServiceResult<ProductAppDetailRespVO> getProductDetail(ProductAppDetailReqVo reqVo) {
        MicroServiceResult<ProductAppDetailRespVO> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }

    @Override
    public MicroServiceResult<List<ProductIdxVO>> getSearchTotalIndices(TotalProductIdxReqVo reqVo) {
        MicroServiceResult<List<ProductIdxVO>> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }

    @Override
    public MicroServiceResult<List<ProductIdxVO>> getSearchIncrIndices(IncrProductIdxReqVo reqVo) {
        MicroServiceResult<List<ProductIdxVO>> microServiceResult = new MicroServiceResult<>();
        microServiceResult.setStatus(404);
        microServiceResult.setMsg("Spring Cloud service is not founded");
        return microServiceResult;
    }
}
