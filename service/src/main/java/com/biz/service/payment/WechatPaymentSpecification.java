package com.biz.service.payment;

import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.gbck.vo.payment.AlipayPaymentVo;
import com.biz.gbck.vo.payment.WechatPaymentVo;
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
public class WechatPaymentSpecification implements Specification<WechatPaymentLogPo> {

    private WechatPaymentVo wechatPaymentVo;

    public WechatPaymentSpecification(WechatPaymentVo wechatPaymentVo) {
        this.wechatPaymentVo = wechatPaymentVo;
    }

    @Override
    public Predicate toPredicate(Root<WechatPaymentLogPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();

        if (StringUtils.isNotBlank(wechatPaymentVo.getBankType())) {
            String sqlName = "%" + wechatPaymentVo.getBankType().trim() + "%";
            predicates.add(criteriaBuilder.like(root.get("bankType").as(String.class), sqlName));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
