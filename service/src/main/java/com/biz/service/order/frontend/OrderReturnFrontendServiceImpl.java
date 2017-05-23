package com.biz.service.order.frontend;

import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.dao.mysql.po.order.OrderReturnAudit;
import com.biz.gbck.dao.mysql.po.order.OrderReturnItem;
import com.biz.gbck.dao.mysql.repository.order.OrderReturnItemRepository;
import com.biz.gbck.dao.mysql.repository.order.OrderReturnRepository;
import com.biz.gbck.dao.mysql.specification.order.OrderReturnSearchSpecification;
import com.biz.gbck.enums.order.AuditStatus;
import com.biz.gbck.enums.order.RefundStatus;
import com.biz.gbck.enums.order.ReturnStatus;
import com.biz.gbck.enums.order.ReturnType;
import com.biz.gbck.vo.order.req.OrderReturnAuditReqVo;
import com.biz.gbck.vo.order.req.OrderReturnListReqVo;
import com.biz.gbck.vo.order.req.OrderReturnSearchReqVo;
import com.biz.service.AbstractRepositorySupportService;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 退货单service
 * Created by liqi1 on 2017/5/18.
 */
@Service
public class OrderReturnFrontendServiceImpl extends AbstractRepositorySupportService<OrderReturn> implements OrderReturnFrontendService{

    @Autowired
    private OrderReturnRepository orderReturnRepository;

    @Override
    protected CommonJpaRepository<OrderReturn, Long> getRepository() {
        return orderReturnRepository;
    }

    @Override
    public OrderReturn getOrderReturn(String returnCode) {
        return orderReturnRepository.findByReturnCode(returnCode);
    }

    @Override
    public Page<OrderReturn> listOrderReturns(OrderReturnListReqVo reqVo) {
        return orderReturnRepository.findAll(new PageRequest(reqVo.getPage()-1,reqVo.getPageSize()));
    }

    @Override
    public Page<OrderReturn> searchOrderReturn(OrderReturnSearchReqVo reqVo) {
        return orderReturnRepository.findAll(new OrderReturnSearchSpecification(reqVo),new PageRequest(reqVo.getPage()-1,reqVo.getPageSize(), Sort.Direction.ASC,"createTimestamp"));
    }



    @Override
    public void auditOrderReturn(OrderReturnAuditReqVo reqVo) {
        OrderReturn orderReturn = orderReturnRepository.findByReturnCode(reqVo.getReturnCode());
        OrderReturnAudit orderReturnAudit = new OrderReturnAudit();
        //设置审核状态为已审核
        orderReturn.setStatus(ReturnStatus.AUDITED);
        //设置退货单更新状态时间
        orderReturn.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        //设置退款时间(条件：通过审核、退货类型为退货且选择立即退款)
        boolean flag = orderReturn.getReturnType().equals(ReturnType.RETURN)&&reqVo.getIsRefundNow().equals("REFUND_NOW")&&reqVo.getAuditStatus().equals(AuditStatus.PASS);
        if(flag){
            orderReturnAudit.setRefundTimestamp(new Timestamp(System.currentTimeMillis()));
            orderReturn.setRefundStatus(RefundStatus.REFUNDED);
        }
        //Vo  Poto
        orderReturnAudit.setAuditContent(reqVo.getAuditContent());
        orderReturnAudit.setAuditor(reqVo.getAuditor());
        orderReturnAudit.setAuditStatus(reqVo.getAuditStatus());
        orderReturnAudit.setRejectReason(reqVo.getRejectReason());
        Timestamp createTimestamp =  new Timestamp(System.currentTimeMillis());
        orderReturnAudit.setAuditTimestamp(createTimestamp);
        orderReturnAudit.setRejectReason(reqVo.getRejectReason());
        orderReturn.setReturnAudit(orderReturnAudit);

        orderReturnRepository.save(orderReturn);

    }
}
