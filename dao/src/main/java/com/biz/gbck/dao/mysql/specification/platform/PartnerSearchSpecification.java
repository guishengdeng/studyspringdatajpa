package com.biz.gbck.dao.mysql.specification.platform;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *合伙人查询规范条件
 */
public class PartnerSearchSpecification implements Specification<PartnerPo>{

	private PartnerSearchVo reqVo;

	public PartnerSearchSpecification(PartnerSearchVo reqVo) {

		this.reqVo = reqVo;
	}

	@Override
	public Predicate toPredicate(Root<PartnerPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = Lists.newArrayList();
		CriteriaBuilder cb;

		if (reqVo.getId() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("id"), reqVo.getId());
			predicates.add(predicate);
		}

		if (reqVo.getPlatformId() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("platformId"),reqVo.getPlatformId());
			predicates.add(predicate);
		}

		if(StringUtils.isNotBlank(reqVo.getCorporateName())){
			String sqlName = "%" + reqVo.getCorporateName().trim() + "%";
			predicates.add(criteriaBuilder.like(root.get("corporateName").as(String.class), sqlName));
		}

		if(StringUtils.isNotBlank(reqVo.getMobile())){
			String sqlName = "%" + reqVo.getMobile().trim() + "%";
			predicates.add(criteriaBuilder.like(root.get("mobile").as(String.class), sqlName));
		}


		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		return criteriaQuery.getRestriction();

	}
}
