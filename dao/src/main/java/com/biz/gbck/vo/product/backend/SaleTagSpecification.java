package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleTag;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 标签搜索翻页
 * Created by lzz on 2017/5/4.
 */
public class SaleTagSpecification implements Specification<SaleTag> {

    private SaleTagSearchVo saleTagSearchVo;

    public SaleTagSpecification(SaleTagSearchVo saleTagSearchVo) {
        this.saleTagSearchVo = saleTagSearchVo;
    }

    @Override
    public Predicate toPredicate(Root<SaleTag> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();

        if (saleTagSearchVo.getStatus() != null) {
            // 分类ID
            Predicate predicate = criteriaBuilder.equal(root.get("status"), saleTagSearchVo.getStatus());
            predicates.add(predicate);
        }

        if (saleTagSearchVo.getSaleStatus() != null) {
            // 审核状态
            Predicate predicate = criteriaBuilder.equal(root.get("saleStatus"), saleTagSearchVo.getSaleStatus());
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(saleTagSearchVo.getName())) {
            String sqlName = "%" + saleTagSearchVo.getName().trim() + "%";
            predicates.add(criteriaBuilder.like(root.get("name").as(String.class), sqlName));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
