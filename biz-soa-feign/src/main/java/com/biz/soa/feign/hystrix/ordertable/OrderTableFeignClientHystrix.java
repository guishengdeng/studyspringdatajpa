/*
package com.biz.soa.feign.hystrix.ordertable;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.soa.feign.client.ordertable.OrderTableFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.List;

*/
/**
 * OrderTableFeignClientHystrix
 *
 * @author guisheng.deng
 * @date 2017年05月19日
 * @reviewer
 * @description
 * @see
 *//*

@Component
public class OrderTableFeignClientHystrix implements OrderTableFeignClient {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @PostConstruct
    public void setup() {
        logger.info("Init " + getClass());
    }

    @Override
    public List<OrderResponseVo> orderRespList(@RequestBody OrderQueryReqVo vo) {
        return null;
    }
    @Override
    public List<Order> listOrder() {
        return null;
    }

    @Override
    public Page<Order> queryOrdersByCondition(@RequestBody OrderQueryReqVo vo) {
        return null;
    }

    @Override
    public List<OrderResponseVo> pageOrder2ListRespVo(Page<Order> page) {
        return null;
    }
}*/
