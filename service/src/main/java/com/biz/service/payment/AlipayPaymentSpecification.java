package com.biz.service.payment;

import com.biz.gbck.dao.mysql.po.payment.AlipayPaymentLogPo;
import com.biz.gbck.vo.payment.AlipayPaymentVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by lzz on 2017/5/15.
 */
public class AlipayPaymentSpecification implements Specification<AlipayPaymentLogPo> {


    private AlipayPaymentVo alipayPaymentVo;

    public AlipayPaymentSpecification(AlipayPaymentVo alipayPaymentVo) {
        this.alipayPaymentVo = alipayPaymentVo;
    }

    @Override
    public Predicate toPredicate(Root<AlipayPaymentLogPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();

        if (StringUtils.isNotBlank(alipayPaymentVo.getBuyerEmail())) {
            String sqlName = "%" + alipayPaymentVo.getBuyerEmail().trim() + "%";
            predicates.add(criteriaBuilder.like(root.get("buyerEmail").as(String.class), sqlName));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
