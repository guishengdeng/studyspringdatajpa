package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.purchase.DeliveryOrder;

import java.util.List;

/**
 * DeliveryOrderService
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
public interface DeliveryOrderService {

    List<DeliveryOrder> findByOrderCodes(Iterable<String> orderCodes);


    DeliveryOrder  getByOrderCode(String orderCode);
}
