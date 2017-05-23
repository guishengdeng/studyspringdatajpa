package com.biz.service.order.backend;

import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.ali.oss.util.OssUtil;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.dao.mysql.po.order.OrderReturnAudit;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 退货单service
 * Created by liqi1 on 2017/5/18.
 */
@Service
public class OrderReturnBackendServiceImpl extends AbstractRepositorySupportService<OrderReturn> implements OrderReturnBackendService {

    /**
     * 是否立即退款
     */
    private static final String IS_REUND_NOW = "REFUND_NOW";

    private static final String NOT_REUND_NOW = "NOT_REFUND_NOW";


    @Autowired
    private OrderReturnRepository orderReturnRepository;

    @Autowired
    private OssConfig config;

    @Override
    protected CommonJpaRepository<OrderReturn, Long> getRepository() {
        return orderReturnRepository;
    }

    @Override
    public OrderReturn getOrderReturn(Long id) {
        OrderReturn orderReturn = orderReturnRepository.findOne(id);
        orderReturn.setImages(getOssResourceUri(orderReturn.getImages()));
        return orderReturn;
    }

    @Override
    public Page<OrderReturn> listOrderReturns(OrderReturnListReqVo reqVo) {
        return orderReturnRepository.findAll(new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize()));
    }

    @Override
    public Page<OrderReturn> searchOrderReturn(OrderReturnSearchReqVo reqVo) {
        Page<OrderReturn> orderReturns = orderReturnRepository.findAll(new OrderReturnSearchSpecification(reqVo), new PageRequest(reqVo.getPage() - 1, reqVo.getPageSize(), Sort.Direction.DESC, "createTimestamp"));
        for (OrderReturn orderReturn:orderReturns) {
            orderReturn.setImages(getOssResourceUri(orderReturn.getImages()));
        }
        return orderReturns;
    }

    @Override
    public void auditOrderReturn(OrderReturnAuditReqVo reqVo) {
        OrderReturn orderReturn = orderReturnRepository.findOne(reqVo.getId());
        OrderReturnAudit orderReturnAudit = new OrderReturnAudit();
        //设置审核状态为已审核
        orderReturn.setStatus(ReturnStatus.AUDITED);
        //设置退货单更新状态时间
        orderReturn.setUpdateTimestamp(DateUtil.now());
        //设置退款时间(条件：通过审核、退货类型为退货且选择立即退款)
        boolean flag = Objects.equals(orderReturn.getReturnType(), ReturnType.RETURN) && Objects.equals(reqVo.getIsRefundNow(), IS_REUND_NOW) && Objects.equals(reqVo.getAuditStatus(), AuditStatus.PASS);
        if (flag) {
            orderReturnAudit.setRefundTimestamp(DateUtil.now());
            orderReturn.setRefundStatus(RefundStatus.REFUNDED);
        }
        //Vo to Po
        orderReturnAudit.setAuditContent(StringUtils.trim(reqVo.getAuditContent()));
        orderReturnAudit.setAuditor(reqVo.getAuditor());
        orderReturnAudit.setAuditStatus(reqVo.getAuditStatus());
        orderReturnAudit.setRejectReason(StringUtils.trim(reqVo.getRejectReason()));
        orderReturnAudit.setAuditTimestamp(DateUtil.now());
        //设置审核详情到退货单
        orderReturn.setReturnAudit(orderReturnAudit);
        orderReturnRepository.save(orderReturn);
    }

    /**
     * 获取图片真实地址
     * @param sources
     * @return
     */
    public List<String> getOssResourceUri(List<String> sources) {
        List<String> images = new ArrayList<>();
        for (String imageName : sources) {
            String imageUrl = OssUtil.getOssResourceUri(config.getProductBucketName(), config.getRemoteEndpoint(), imageName);
            images.add(imageUrl);
        }
        return images;
    }
}
