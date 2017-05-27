package com.biz.service.product.backend;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrderItem;
import com.biz.gbck.vo.product.backend.ProductSaleDetailQueryReqVo;
import com.biz.gbck.vo.product.frontend.ProductSaleDetailRespVo;
import org.springframework.data.domain.Page;

/**
 * ProductSaleDetailBackendService
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
public interface ProductSaleDetailBackendService {

    Page<PurchaseOrderItem> queryOrderItemByCondition(ProductSaleDetailQueryReqVo vo);


    Page<ProductSaleDetailRespVo>  pagePo2PageRespVo(Page<PurchaseOrderItem> page);
}
