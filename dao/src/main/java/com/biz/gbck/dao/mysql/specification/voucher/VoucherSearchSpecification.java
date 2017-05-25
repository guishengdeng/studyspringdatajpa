package com.biz.gbck.dao.mysql.specification.voucher;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.google.common.collect.Lists;

public class VoucherSearchSpecification implements Specification<VoucherTypePo>{

	private VoucherSearchVo reqVo;
	
	public VoucherSearchSpecification(VoucherSearchVo reqVo){
		this.reqVo = reqVo;
	}
	
	public Predicate toPredicate(Root<VoucherTypePo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = Lists.newArrayList();
		if(reqVo.getName() != null){
			Predicate predicate = cb.equal(root.get("name"), reqVo.getName());
			predicates.add(predicate);
		}else if(reqVo.getIssuerName() != null){
			Predicate predicate = cb.equal(root.get("issuerName"), reqVo.getIssuerName());
			predicates.add(predicate);
		}else if(reqVo.getStartTime() != null){
			Predicate predicate = cb.equal(root.get("startTime"), reqVo.getStartTime());
			predicates.add(predicate);
		}
		
		if(StringUtils.isNotBlank(reqVo.getName())){
			String sqlName = "%" + reqVo.getName().trim() + "%";
			predicates.add(cb.like(root.get("name").as(String.class), sqlName));
		}
		
		query.where(predicates.toArray(new Predicate[predicates.size()]));
		return query.getRestriction();
	}

}
