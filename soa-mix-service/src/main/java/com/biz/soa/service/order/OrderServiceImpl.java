/*
package com.biz.soa.service.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.dao.mysql.repository.org.PlatformRepository;
import com.biz.gbck.dao.mysql.specification.order.OrderSearchSpecification;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.order.backend.OrderBackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

*/
/**
 * OrderServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 *//*

@Service
public class OrderServiceImpl extends AbstractBaseService implements OrderBackendService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> queryOrdersByCondition(OrderQueryReqVo vo) {

         return orderRepository.findAll(new OrderSearchSpecification(vo),new PageRequest(vo.getPage()-1,vo.getPageSize()));

    }

    */
/**
     * 将page<Order> 转化成List<OrderResponseVo>
     *//*

    @Override
    public List<OrderResponseVo> pageOrder2ListRespVo(Page<Order> page) {
        Map<Long,String> companyIdsAndCompanyName = new HashMap<>();
        Map<Long,String> partnerIdsAndPartnerName = new HashMap<>();
        Set<Long> companyIds = new HashSet<Long>();
        Set<Long> partnerIds = new HashSet<Long>();
        if(page != null){
            for(Order order : page) {
                companyIds.add(order.getCompanyId());
                partnerIds.add(order.getSellerId());
            }
        }
        List<PlatformPo> platformPos = platformRepository.findAll(companyIds);
        List<PartnerPo> partnerPos = partnerRepository.findAll(partnerIds);
        for(PartnerPo partnerPo:partnerPos){
            partnerIdsAndPartnerName.put(partnerPo.getId(),partnerPo.getName());
        }
        for (PlatformPo platformPo : platformPos) {
            companyIdsAndCompanyName.put(platformPo.getId(), platformPo.getName());
        }
        List<OrderResponseVo> responseVos = new ArrayList<OrderResponseVo>();
        if(page != null){
            for(Order order : page){
                OrderResponseVo responseVo = new OrderResponseVo();
                String companyName = companyIdsAndCompanyName.get(order.getCompanyId());
                String partnerName = partnerIdsAndPartnerName.get(order.getSellerId());
                responseVo.setId(order.getId());
                responseVo.setPlatFormCompanyName(companyName);
                responseVo.setPartnerName(partnerName);
                responseVo.setOrderAmount(order.getOrderAmount());
                responseVo.setOrderCode(order.getOrderCode());
                responseVo.setOrderDate(order.getCreateTimestamp());
                responseVos.add(responseVo);
            }
        }
        return responseVos;
    }

}*/
