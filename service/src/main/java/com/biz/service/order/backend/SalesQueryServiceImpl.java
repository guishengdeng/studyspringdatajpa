package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopRepository;
import com.biz.gbck.dao.mysql.specification.sale.SalesQuerySpecification;
import com.biz.gbck.vo.order.req.SalesQueryVo;
import com.biz.gbck.vo.order.req.SalesSearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2017/5/23.
 */
public class SalesQueryServiceImpl implements SalesQueryService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Page<Order> findList(SalesSearchVo salesSearchVo) {

        Page<Order> page = orderRepository.findAll(new SalesQuerySpecification(salesSearchVo),
                new PageRequest(salesSearchVo.getPage() - 1, salesSearchVo.getPageSize(), new Sort(Sort.Direction.ASC, "orderCode")));
        List<Order> orderList = page.getContent();
        List<SalesQueryVo> saleQueryList =new ArrayList<SalesQueryVo>();
        /**
         * 遍历取值，并放入SalesQueryVo
         */
        for (Order order : orderList) {

            SalesQueryVo salesQueryVo = new SalesQueryVo();
            if (StringUtils.isNotBlank(order.getOrderCode())) {
                salesQueryVo.setOrderCode(order.getOrderCode());
            }
            salesQueryVo.setOrderTime(order.getCreateTimestamp());
            if (order.getShipping().getDeliveryDate() != null)
                salesSearchVo.setDeliveryTime(order.getShipping().getDeliveryDate());
            if (order.getOrderAmount() != null) {
                salesQueryVo.setOrderAmount(order.getOrderAmount());
            }
            salesQueryVo.setStatus(order.getStatus());
            ShopPo shop =shopRepository.findOne(order.getShopId());
            if (shop != null){
                salesQueryVo.setMobile(shop.getMobile());
            }
            if (StringUtils.isNotBlank(order.getConsignee().getAddress()))salesQueryVo.setAddress(order.getConsignee().getAddress());
            saleQueryList.add(salesQueryVo);
        }
        Page<SalesQueryVo> salePage = new PageImpl<SalesQueryVo>(saleQueryList,
                new PageRequest(salesSearchVo.getPage()-1,salesSearchVo.getPageSize()),orderList.size());
        return null;
    }
}
