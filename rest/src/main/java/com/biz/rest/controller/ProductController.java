package com.biz.rest.controller;

import com.biz.gbck.vo.product.gbck.response.ProductAppDetailRespVO;
import com.biz.gbck.vo.product.gbck.response.ProductAppListItemVo;
import com.biz.soa.feign.ProductFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Lists;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
public class ProductController extends BaseRestController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @RequestMapping(value = "/list")
    public JSONResult productList(HttpServletRequest request, HttpServletResponse response) {
        return new JSONResult(Lists.<ProductAppListItemVo>newArrayList());
    }

    @RequestMapping(value = "/detail")
    public JSONResult productDetail(HttpServletRequest request, HttpServletResponse response) {
        return new JSONResult(new ProductAppDetailRespVO());
    }

    @RequestMapping(value = "/test")
    public String test() {
        return productFeignClient.getTestString();
    }
}
