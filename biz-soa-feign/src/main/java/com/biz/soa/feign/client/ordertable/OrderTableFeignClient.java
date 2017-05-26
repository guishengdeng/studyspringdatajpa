/*
package com.biz.soa.feign.client.ordertable;

import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.service.order.backend.OrderBackendService;
import com.biz.soa.feign.hystrix.ordertable.OrderTableFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

*/
/**
 * OrderTableFeignClient
 *
 * @author guisheng.deng
 * @date 2017年05月19日
 * @reviewer
 * @description
 * @see
 *//*

@FeignClient(name = "soa-mix-service",fallback = OrderTableFeignClientHystrix.class)
public interface OrderTableFeignClient extends OrderBackendService{


    @RequestMapping(value = "soa/orderService/list")
    List<OrderResponseVo> orderRespList(@RequestBody OrderQueryReqVo vo);
}
*/
