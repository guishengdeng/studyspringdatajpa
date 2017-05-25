package com.biz.rest.controller.product;

import com.biz.gbck.vo.product.gbck.request.ProductAppDetailReqVo;
import com.biz.gbck.vo.product.gbck.request.ProductAppListReqVo;
import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListRespVO;
import com.biz.gbck.vo.soa.MicroServiceResult;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.soa.feign.client.product.ProductFeignClient;
import com.biz.support.web.handler.JSONResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseRestController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @RequestMapping(value = "/search")
    public JSONResult productList(HttpServletRequest request, HttpServletResponse response) {
        ProductAppListReqVo reqVo = RestUtil.parseBizData(request, ProductAppListReqVo.class);
        // TODO 调用用户服务, 设置价格组ID和上级采购单位ID
        reqVo.setPriceGroupId(1L);
        reqVo.setSellerId(1L);
        MicroServiceResult<ProductAppListRespVO> productSearchResult = productFeignClient.getProductSearchResult(reqVo);
        if (productSearchResult.getStatus() == MicroServiceResult.SUCCESS_STATUS) {
            return new JSONResult(productSearchResult.getData());
        } else {
            return new JSONResult(productSearchResult.getStatus(), productSearchResult.getMsg());
        }
    }

    @RequestMapping(value = "/detail")
    public JSONResult productDetail(HttpServletRequest request, HttpServletResponse response) {
        ProductAppDetailReqVo reqVo = RestUtil.parseBizData(request, ProductAppDetailReqVo.class);
        // TODO 调用用户服务, 设置价格组ID和上级采购单位ID
        reqVo.setPriceGroupId(1L);
        reqVo.setSellerId(1L);
        MicroServiceResult<ProductAppDetailRespVO> productDetail = productFeignClient.getProductDetail(reqVo);
        if (productDetail.getStatus() == MicroServiceResult.SUCCESS_STATUS) {
            return new JSONResult(productDetail.getData());
        } else {
            return new JSONResult(productDetail.getStatus(), productDetail.getMsg());
        }
    }
}
