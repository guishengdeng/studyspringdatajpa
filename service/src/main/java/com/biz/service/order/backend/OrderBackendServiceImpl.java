package com.biz.service.order.backend;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.order.OrderRepository;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.dao.mysql.specification.order.OrderSearchSpecification;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.biz.gbck.vo.order.resp.OrderResponseVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.org.interfaces.PlatformService;
import com.biz.service.partner.interfaces.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * OrderBackendServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月19日
 * @reviewer
 * @description
 * @see
 */
@Service
public class OrderBackendServiceImpl extends AbstractBaseService implements OrderBackendService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private PartnerService partnerService;
    private OrderQueryReqVo reqVo;
    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> queryOrdersByCondition(OrderQueryReqVo vo) {
        this.reqVo = vo;
        List<PlatformPo> platformPos = platformService.listByName(vo.getPlatFormCompanyName());
        List<PartnerPo> partnerPos = partnerService.listByName(vo.getPartnerName());
        return orderRepository.findAll(new OrderSearchSpecification(vo,platformPos,partnerPos),new PageRequest(vo.getPage()-1,vo.getPageSize()));
    }

    @Override
    public Page<OrderResponseVo> pageOrder2PageRespVo(Page<Order> page) {
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
        List<PlatformPo> platformPos = platformService.findByIds(companyIds);
        List<PartnerPo> partnerPos = partnerService.findByIds(partnerIds);
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
        Page<OrderResponseVo> responseVoPage = new PageImpl<OrderResponseVo>
                (responseVos,new PageRequest(reqVo.getPage()-1,reqVo.getPageSize()),responseVos.size());
        return responseVoPage;
    }
}