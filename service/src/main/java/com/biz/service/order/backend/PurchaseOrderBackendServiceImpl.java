package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrder;
import com.biz.gbck.dao.mysql.repository.purchase.PurchaseOrderRepository;
import com.biz.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PurchaseOrderBackendServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
@Service
public class PurchaseOrderBackendServiceImpl extends AbstractBaseService implements PurchaseOrderBackendService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Override
    public List<PurchaseOrder> findByIds(Iterable<Long> ids) {
        return purchaseOrderRepository.findAll(ids);
    }
}