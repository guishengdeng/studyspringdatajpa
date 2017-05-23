package com.biz.gbck.dao.mysql.specification.order;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.vo.order.req.OrderReturnSearchReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liqi1 on 2017/5/18.
 */
public class OrderReturnSearchSpecification implements Specification<OrderReturn> {

    private OrderReturnSearchReqVo reqVo;

    public OrderReturnSearchSpecification(OrderReturnSearchReqVo reqVo) {
        this.reqVo = reqVo;
    }


    @Override
    public Predicate toPredicate(Root<OrderReturn> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = Lists.newArrayList();

        /**
         *状态
         */
        if(reqVo.getStatus() != null){
            Predicate predicate = criteriaBuilder.equal(root.get("status"),reqVo.getStatus());
            predicates.add(predicate);
        }

        /**
         * 退款状态
         */
        if(reqVo.getRefundStatus() != null){
            Predicate predicate =criteriaBuilder.equal(root.get("refundStatus"),reqVo.getRefundStatus());
            predicates.add(predicate);
        }

        /**
         * 售后类型
         */
        if (reqVo.getReturnType() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("returnType"), reqVo.getReturnType());
            predicates.add(predicate);
        }

        /**
         * 原订单号
         */
        if (StringUtils.isNotBlank(reqVo.getOrderCode())) {
            Predicate predicate = criteriaBuilder.like(root.get("order").get("orderCode"), "%" + StringUtils.trim(reqVo.getOrderCode()) + "%");
            predicates.add(predicate);
        }

        /**
         * 退货单号
         */
        if (StringUtils.isNotBlank(reqVo.getReturnCode())) {
            Predicate predicate = criteriaBuilder.like(root.get("returnCode"), "%" + StringUtils.trim(reqVo.getReturnCode()) + "%");
            predicates.add(predicate);
        }

        /**
         * 根据退货日期查询
         */
        if (StringUtils.isNotBlank(reqVo.getStartTime()) && StringUtils.isNotBlank(reqVo.getEndTime())) {
            Timestamp startTime = DateUtil.getTimestamp(reqVo.getStartTime());
            Timestamp endTime = DateUtil.getTimestamp(reqVo.getEndTime());
            Predicate predicate = criteriaBuilder.between(root.get("createTimestamp"),startTime,endTime);
            predicates.add(predicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

        return criteriaQuery.getRestriction();
    }
}
