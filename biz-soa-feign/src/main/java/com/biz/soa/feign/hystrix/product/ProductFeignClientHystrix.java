package com.biz.soa.feign.hystrix.product;

import com.biz.gbck.vo.product.ProductFilterListItemVO;
import com.biz.gbck.vo.product.backend.BootstrapTablePageResult;
import com.biz.gbck.vo.product.backend.ProductFilterListReqVO;
import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.request.PurchaseProductReqVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.product.gbck.response.PurchaseProductItemVO;
import com.biz.gbck.vo.search.IncrProductIdxReqVo;
import com.biz.gbck.vo.search.ProductIdxVO;
import com.biz.gbck.vo.search.TotalProductIdxReqVo;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.soa.feign.client.product.ProductFeignClient;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/17 00:22.
 */
@Component
public class ProductFeignClientHystrix implements ProductFeignClient {
    @Override
    public MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVO>> getProductFilters(ProductFilterListReqVO reqVO) {
        return null;
    }

    @Override
    public MicroServiceResult<List<ProductFilterListItemVO>> getProductSearchResultFilters(Long categoryId) {
        return null;
    }

    @Override
    public MicroServiceResult<ProductAppListRespVO> getProductSearchResult(ProductAppListReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<ProductAppDetailRespVO> getProductDetail(ProductAppDetailReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<List<ProductIdxVO>> getSearchTotalIndices(TotalProductIdxReqVo reqVo) {
        return null;
    }

    @Override
    public MicroServiceResult<List<ProductIdxVO>> getSearchIncrIndices(IncrProductIdxReqVo reqVo) {
        return null;
    }

    @Override
    public List<PurchaseProductItemVO> getPurchaseProducts(PurchaseProductReqVO reqVO) {
        return null;
    }
}
