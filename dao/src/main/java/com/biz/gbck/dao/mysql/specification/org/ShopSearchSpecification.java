package com.biz.gbck.dao.mysql.specification.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 *商户查询规范条件
 */
public class ShopSearchSpecification implements Specification<ShopPo>{

	private ShopSearchVo reqVo;

	public ShopSearchSpecification(ShopSearchVo reqVo) {

		this.reqVo = reqVo;
	}

	@Override
	public Predicate toPredicate(Root<ShopPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = Lists.newArrayList();
		CriteriaBuilder cb;

		if (reqVo.getId() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("id"), reqVo.getId());
			predicates.add(predicate);
		}

		if(StringUtils.isNotBlank(reqVo.getName())){
			String sqlName = "%" + reqVo.getName().trim() + "%";
			predicates.add(criteriaBuilder.like(root.get("name").as(String.class), sqlName));
		}

		if(StringUtils.isNotBlank(reqVo.getMobile())){
			String sqlName = "%" + reqVo.getMobile().trim() + "%";
			predicates.add(criteriaBuilder.like(root.get("mobile").as(String.class), sqlName));
		}

		if(reqVo.getShopType() != null){
			Predicate predicate = criteriaBuilder.equal(root.get("shopType"),reqVo.getShopType());
			predicates.add(predicate);
		}

		if (reqVo.getAuditStatus() != null) {
		    CriteriaBuilder.In in = criteriaBuilder.in(root.get("detailAuditStatus"));
			in.value(reqVo.getAuditStatus());
			if(reqVo.getAuditStatusTwo() != null){
				in.value(reqVo.getAuditStatusTwo());
			}
			predicates.add(in);
			CriteriaBuilder.In inTwo = criteriaBuilder.in(root.get("qualificationAuditStatus"));
			inTwo.value(reqVo.getAuditStatus());
			if(reqVo.getAuditStatusTwo() != null){
				inTwo.value(reqVo.getAuditStatusTwo());
			}
			predicates.add(inTwo);
		}


		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		return criteriaQuery.getRestriction();

	}
}
