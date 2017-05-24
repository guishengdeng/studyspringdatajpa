package com.biz.gbck.dao.mysql.repository.order;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.order.OrderReturn;
import com.biz.gbck.vo.order.req.OrderReturnSearchReqVo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yangzichun
 * @date 2017/4/24
 */
public class OrderReturnRepositoryImpl extends CommonJpaRepositoryBean<OrderReturn, Long> implements OrderReturnDao {
    @Autowired
    public OrderReturnRepositoryImpl(EntityManager em) {
        super(OrderReturn.class, em);
    }

    @Override
    public Page<OrderReturn> search(OrderReturnSearchReqVo reqVo, Pageable page) {
        return super.findAll((root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();

            /**
             *状态
             */
            if (reqVo.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), reqVo.getStatus()));
            }

            /**
             * 退款状态
             */
            if (reqVo.getRefundStatus() != null) {
                Predicate predicate = cb.equal(root.get("refundStatus"), reqVo.getRefundStatus());
                predicates.add(predicate);
            }

            /**
             * 售后类型
             */
            if (reqVo.getReturnType() != null) {
                Predicate predicate = cb.equal(root.get("returnType"), reqVo.getReturnType());
                predicates.add(predicate);
            }

            /**
             * 原订单号
             */
            if (StringUtils.isNotBlank(reqVo.getOrderCode())) {
                Predicate predicate = cb.like(root.get("order").get("orderCode"), "%" + StringUtils.trim(reqVo
                        .getOrderCode()) + "%");
                predicates.add(predicate);
            }

            /**
             * 退货单号
             */
            if (StringUtils.isNotBlank(reqVo.getReturnCode())) {
                Predicate predicate = cb.like(root.get("returnCode"), "%" + StringUtils.trim(reqVo.getReturnCode()) +
                        "%");
                predicates.add(predicate);
            }

            /**
             * 根据退货日期查询
             */
            if (StringUtils.isNotBlank(reqVo.getStartTime()) && StringUtils.isNotBlank(reqVo.getEndTime())) {
                Timestamp startTime = DateUtil.getTimestamp(reqVo.getStartTime());
                Timestamp endTime = DateUtil.getTimestamp(reqVo.getEndTime());
                Predicate predicate = cb.between(root.get("createTimestamp"), startTime, endTime);
                predicates.add(predicate);
            }
            query.orderBy(cb.desc(root.get("createTimestamp")));

            query.where(predicates.toArray(new Predicate[predicates.size()]));

            return query.getRestriction();
        }, page);
    }
}
