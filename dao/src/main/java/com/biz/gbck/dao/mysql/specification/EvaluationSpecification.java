package com.biz.gbck.dao.mysql.specification;

import com.biz.gbck.dao.mysql.po.product.bbc.Evaluation;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.evaluation.backend.EvaluationQueryRequestVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 评论查询
 *
 * @author lei
 * @usage
 * @reviewer
 * @since 2017/2/10
 */
public class EvaluationSpecification implements Specification<Evaluation> {

    private EvaluationQueryRequestVo vo;

    public EvaluationSpecification(EvaluationQueryRequestVo vo) {
        this.vo = vo;
    }

    @Override
    public Predicate toPredicate(Root<Evaluation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        query.orderBy(cb.desc(root.get("createTimestamp")));
        // 查询未删除的

        if (StringUtils.isNotBlank(vo.getProductCode())) {
            Predicate predicate = cb.equal(root.get("product").get("productCode"), vo.getProductCode());
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(vo.getProductName())) {
            Predicate predicate = cb.like(root.get("product").<String>get("name"), "%" + vo.getProductName() + "%");
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(vo.getVendorName())) {
            Predicate predicate = cb.like(root.get("vendor").<String>get("vendorName"), "%" + vo.getVendorName() + "%");
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(vo.getOrderCode())) {
            Predicate predicate = cb.like(root.<String>get("orderCode"), "%" + vo.getVendorName() + "%");
            predicates.add(predicate);
        }

        if (vo.getMemberId() != null) {
            Predicate predicate = cb.equal(root.get("memberId"), vo.getMemberId());
            predicates.add(predicate);
        }

        if (vo.getStatus() != null) {
            Predicate predicate = cb.equal(root.get("commonStatus"), CommonStatusEnum.ENABLE);
            predicates.add(predicate);
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return query.getRestriction();
    }
}
