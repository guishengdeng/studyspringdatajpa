package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.purchase.DeliveryOrder;
import com.biz.gbck.dao.mysql.repository.order.DeliveryOrderRepository;
import com.biz.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DeliverOrderServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
@Service
public class DeliverOrderServiceImpl extends AbstractBaseService implements DeliveryOrderService{
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Override
    public DeliveryOrder getByOrderCode(String orderCode) {
        return deliveryOrderRepository.findByOrderCode(orderCode);
    }

    public List<DeliveryOrder> findByOrderCodes(Iterable<String> orderCodes) {
        return null;
    }
}