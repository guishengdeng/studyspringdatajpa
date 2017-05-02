package com.biz.gbck.dao.mysql.specification.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.vo.admin.AdminReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * AdminDynamicSpecification
 *
 * @author guisheng.deng
 * @date 2017年04月28日
 * @reviewer
 * @description:用户动态查询
 * @see
 */
public class AdminDynamicSpecification implements Specification<Admin> {

    private AdminReqVo adminReqVo;
    public AdminDynamicSpecification(AdminReqVo adminReqVo){
        this.adminReqVo = adminReqVo;
    }
    @Override
    public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates= Lists.newArrayList();
        if(adminReqVo.getStatus()!=null){
            Predicate predicate = criteriaBuilder.equal(root.get("status"), adminReqVo.getStatus());
            predicates.add(predicate);
        }
        if(StringUtils.isNotBlank(adminReqVo.getUsername())){
            //模糊查询
            String sqlUsername = "%" + adminReqVo.getUsername().trim() + "%";
            predicates.add(criteriaBuilder.like(root.get("username").as(String.class), sqlUsername));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}