package com.biz.gbck.dao.mysql.specification.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.vo.demo.CatSearchVO;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *商户查询规范条件
 */
public class ShopSearchSpecification implements Specification<ShopPo> {

	private ShopSearchVo reqVo;

	public ShopSearchSpecification(ShopSearchVo reqVo) {

		this.reqVo = reqVo;
	}

	@Override
	public Predicate toPredicate(Root<ShopPo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = Lists.newArrayList();

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

		if(StringUtils.isNotBlank(reqVo.getShopTypeName())){
			Predicate predicate = criteriaBuilder.equal(root.get("shopType").get("name"),reqVo.getShopTypeName());
			predicates.add(predicate);
		}

		if (reqVo.getAuditStatus() != null) {
			Predicate predicate1 = criteriaBuilder.equal(root.get("detailAuditStatus"), reqVo.getAuditStatus());
			predicates.add(predicate1);
			Predicate predicate2 = criteriaBuilder.equal(root.get("qualificationAuditStatus"), reqVo.getAuditStatus());
			predicates.add(predicate2);

		}





		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		return criteriaQuery.getRestriction();

	}
}
