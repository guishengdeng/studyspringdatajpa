package com.biz.gbck.dao.mysql.specification.demo;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.product.backend.PlatformProductAuditListReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 猫的搜索翻页
 * @author defei
 * @date 2017/4/25
 */
public class CatSearchSpecification implements Specification<CatPO> {

	private CatSearchVO reqVo;

	public CatSearchSpecification(CatSearchVO reqVo) {

		this.reqVo = reqVo;
	}

	@Override
	public Predicate toPredicate(Root<CatPO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = Lists.newArrayList();

		if (reqVo.getStatus() != null) {
			// 分类ID
			Predicate predicate = criteriaBuilder.equal(root.get("status"), reqVo.getSaleStatus());
			predicates.add(predicate);
		}

		if (reqVo.getSaleStatus() != null) {
			// 审核状态
			Predicate predicate = criteriaBuilder.equal(root.get("saleStatus"), reqVo.getSaleStatus());
			predicates.add(predicate);
		}
		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		return criteriaQuery.getRestriction();

	}
}
