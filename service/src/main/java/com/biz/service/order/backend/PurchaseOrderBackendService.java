package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrder;

import java.util.List;

/**
 * PurchaseOrderBackendService
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
public interface PurchaseOrderBackendService {

    List<PurchaseOrder> findByIds(Iterable<Long> ids);
}
