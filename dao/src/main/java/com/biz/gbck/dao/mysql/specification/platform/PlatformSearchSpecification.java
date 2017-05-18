package com.biz.gbck.dao.mysql.specification.platform;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *平台公司查询规范条件
 */
public class PlatformSearchSpecification implements Specification<PlatformPo>{

	private PlatformSearchVo reqVo;

	public PlatformSearchSpecification(PlatformSearchVo reqVo) {

		this.reqVo = reqVo;
	}

	@Override
	public Predicate toPredicate(Root<PlatformPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = Lists.newArrayList();
		CriteriaBuilder cb;

		if (reqVo.getId() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("id"), reqVo.getId());
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
