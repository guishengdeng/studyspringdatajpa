package com.biz.gbck.dao.mysql.specification.sale;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.order.req.SalesSearchVo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by lzz on 2017/5/23.
 */
public class SalesQuerySpecification implements Specification<Order>{

    private SalesSearchVo salesSearchVo;

    public SalesQuerySpecification(SalesSearchVo salesSearchVo) {
        this.salesSearchVo = salesSearchVo;
    }

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
