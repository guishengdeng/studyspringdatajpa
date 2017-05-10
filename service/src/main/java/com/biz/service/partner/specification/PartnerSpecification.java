package com.biz.service.partner.specification;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.enums.partner.ApprovalStatus;
import com.biz.vo.partner.PartnerSearchReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by haibin.tang on 2017/5/10.
 */
public class PartnerSpecification implements Specification<PartnerPo> {

    private PartnerSearchReqVo partnerSearchReqVo;

    public PartnerSpecification(PartnerSearchReqVo partnerSearchReqVo) {
        this.partnerSearchReqVo = partnerSearchReqVo;
    }

    @Override
    public Predicate toPredicate(Root<PartnerPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTimestamp")));
        if (partnerSearchReqVo == null) {
            return criteriaQuery.getRestriction();
        }
        List<Predicate> predicates = Lists.newArrayList();
        if (partnerSearchReqVo.getApprovalStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.<ApprovalStatus>get("approvalStatus"), partnerSearchReqVo.getApprovalStatus()));
        }
        if (StringUtils.isNotBlank(partnerSearchReqVo.getName())) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + partnerSearchReqVo.getName() + "%"));
        }
        if (StringUtils.isNotBlank(partnerSearchReqVo.getCorporateName())) {
            predicates.add(criteriaBuilder.like(root.get("corporateName"), "%" + partnerSearchReqVo.getCorporateName() + "%"));
        }
        if (StringUtils.isNotBlank(partnerSearchReqVo.getOperator())) {
            predicates.add(criteriaBuilder.equal(root.get("operator"), partnerSearchReqVo.getOperator()));
        }
        if (StringUtils.isNotBlank(partnerSearchReqVo.getSubmitStartTime()) && StringUtils.isNotBlank(partnerSearchReqVo.getSubmitEndTime())) {
            predicates.add(criteriaBuilder.between(root.get("createTimestamp"), DateUtil.getTimestamp(partnerSearchReqVo.getSubmitStartTime()), DateUtil.getTimestamp(partnerSearchReqVo.getSubmitEndTime())));
        }
        if (StringUtils.isNotBlank(partnerSearchReqVo.getOptionStartTime()) && StringUtils.isNotBlank(partnerSearchReqVo.getOptionEndTime())) {
            predicates.add(criteriaBuilder.between(root.get("operatorTime"), DateUtil.getTimestamp(partnerSearchReqVo.getOptionStartTime()), DateUtil.getTimestamp(partnerSearchReqVo.getOptionEndTime())));
        }

        if (CollectionUtils.isNotEmpty(predicates)) {
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        return criteriaQuery.getRestriction();
    }
}
