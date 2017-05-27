package com.biz.service.product.backend;

import com.biz.gbck.dao.mysql.po.purchase.DeliveryOrder;
import com.biz.gbck.dao.mysql.po.purchase.ProductItem;
import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrder;
import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrderItem;
import com.biz.gbck.dao.mysql.repository.purchase.PurchaseOrderItemRepository;
import com.biz.gbck.dao.mysql.specification.pur.ProductSaleDetailSpecification;
import com.biz.gbck.enums.purchase.PurchaseOrderStatus;
import com.biz.gbck.vo.product.backend.ProductSaleDetailQueryReqVo;
import com.biz.gbck.vo.product.frontend.ProductSaleDetailRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.backend.DeliveryOrderService;
import com.biz.service.order.backend.PurchaseOrderBackendService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * ProductSaleDetailBackendServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
@Service
public class ProductSaleDetailBackendServiceImpl extends AbstractBaseService implements ProductSaleDetailBackendService {
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;
    @Autowired
    private PurchaseOrderBackendService purchaseOrderBackendService;
    @Autowired
    private DeliveryOrderService deliveryOrderService;

    private ProductSaleDetailQueryReqVo vo;
    @Override
    public Page<PurchaseOrderItem> queryOrderItemByCondition(ProductSaleDetailQueryReqVo vo) {
        this.vo = vo;
        return purchaseOrderItemRepository.findAll(new ProductSaleDetailSpecification(vo),new PageRequest(vo.getPage()-1,vo.getPageSize()));
    }

    @Override
    public Page<ProductSaleDetailRespVo> pagePo2PageRespVo(Page<PurchaseOrderItem> page) {
        List<ProductSaleDetailRespVo> respVoList = Lists.newArrayList();
        Map<Long,PurchaseOrder> purchaseOrderIdsAndPurchaseOrders = new HashMap<Long,PurchaseOrder>();
        Map<Long,DeliveryOrder> purchaseOrderIdsAndDeliveryOrders = new HashMap<Long,DeliveryOrder>();
        //采购单和采购单详情是一对多的关系
        Set<Long> purchaseOrderIds = new HashSet<>();
        //通过订单编号去查询,DeliveryOrder类,从而获得出库日期。

        if(page != null){
            for(PurchaseOrderItem item : page){
                purchaseOrderIds.add(item.getPurchaseOrder().getId());
            }
        }
        List<PurchaseOrder> purchaseOrders = purchaseOrderBackendService.findByIds(purchaseOrderIds);
        for(PurchaseOrder purchaseOrder : purchaseOrders){
            purchaseOrderIdsAndPurchaseOrders.put(purchaseOrder.getId(),purchaseOrder);
            DeliveryOrder deliveryOrder = deliveryOrderService.getByOrderCode(purchaseOrder.getOrderCode());
            purchaseOrderIdsAndDeliveryOrders.put(purchaseOrder.getId(),deliveryOrder);
        }
        if(page != null){
            for(PurchaseOrderItem purchaseOrderItem : page){
                ProductSaleDetailRespVo respVo = new ProductSaleDetailRespVo();
                Long  purchaseOrderId = purchaseOrderItem.getPurchaseOrder().getId();
                ProductItem productItem = purchaseOrderItem.getProductItem();
                PurchaseOrder purchaseOrder = purchaseOrderIdsAndPurchaseOrders.get(purchaseOrderId);
                respVo.setOrderCode(purchaseOrder.getOrderCode());
                respVo.setCreateTimeStamp(purchaseOrder.getCreateTimestamp());
                respVo.setStatus(purchaseOrder.getStatus());
                respVo.setName(productItem.getName());
                respVo.setPrice(productItem.getPrice());
                respVo.setProductCode(productItem.getProductCode());
                respVo.setQuantity(productItem.getQuantity());
                respVo.setUnit(productItem.getUnit());
                respVo.setDeliveryTime(purchaseOrderIdsAndDeliveryOrders.get(purchaseOrderId).getDeliveryTime());
                respVo.setSn(purchaseOrderIdsAndDeliveryOrders.get(purchaseOrderId).getSn());
                respVoList.add(respVo);
            }
        }
        Page<ProductSaleDetailRespVo> pageRespVo
                = new PageImpl<ProductSaleDetailRespVo>(respVoList,new PageRequest(vo.getPage()-1,vo.getPageSize()),respVoList.size());
        return pageRespVo;
    }
}