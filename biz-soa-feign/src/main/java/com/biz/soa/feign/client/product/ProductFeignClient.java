package com.biz.soa.feign.client.product;

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
import com.biz.soa.feign.hystrix.product.ProductFeignClientHystrix;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by david-liu on 2017/05/04 14:30.
 */
@FeignClient(name = "depotnextdoor-product", fallback = ProductFeignClientHystrix.class)
public interface ProductFeignClient {

    @RequestMapping(value = "/soa/product/filter/list", method = RequestMethod.POST)
    MicroServiceResult<BootstrapTablePageResult<ProductFilterListItemVO>> getProductFilters(ProductFilterListReqVO reqVO);

    @RequestMapping(value = "/soa/product/filter/app/searchResult")
    MicroServiceResult<List<ProductFilterListItemVO>> getProductSearchResultFilters(Long categoryId);

    @RequestMapping(value = "/soa/product/app/list", method = RequestMethod.POST)
    MicroServiceResult<ProductAppListRespVO> getProductSearchResult(@RequestBody ProductAppListReqVo reqVo);

    @RequestMapping(value = "/soa/product/app/detail", method = RequestMethod.POST)
    MicroServiceResult<ProductAppDetailRespVO> getProductDetail(@RequestBody ProductAppDetailReqVo reqVo);

    @RequestMapping(value = "/soa/product/search/totalIndices", method = RequestMethod.POST)
    MicroServiceResult<List<ProductIdxVO>> getSearchTotalIndices(@RequestBody TotalProductIdxReqVo reqVo);

    @RequestMapping(value = "/soa/product/search/incrIndices", method = RequestMethod.POST)
    MicroServiceResult<List<ProductIdxVO>> getSearchIncrIndices(@RequestBody IncrProductIdxReqVo reqVo);

    @RequestMapping(value = "/soa/product/purchaseProducts", method = RequestMethod.POST)
    MicroServiceResult<List<PurchaseProductItemVO>> getPurchaseProducts(@RequestBody PurchaseProductReqVO reqVO);

}
