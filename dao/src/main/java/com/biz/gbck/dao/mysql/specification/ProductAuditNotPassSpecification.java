package com.biz.gbck.dao.mysql.specification;

import com.biz.gbck.dao.mysql.po.product.bbc.ProductAudit;
import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.gbck.vo.product.backend.VendorProductListReqVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/22
 */
public class ProductAuditNotPassSpecification implements Specification<ProductAudit> {

    private VendorProductListReqVo reqVo;

    public ProductAuditNotPassSpecification(VendorProductListReqVo reqVo) {
        this.reqVo = reqVo;
    }

    @Override
    public Predicate toPredicate(Root<ProductAudit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTimestamp")));
        // 查询未删除的
        predicates.add(criteriaBuilder.equal(root.get("deleteFlag").as(Boolean.class), Boolean.FALSE));
        if (reqVo.getVendorId() != null) {
            // 商家ID
            Predicate predicate = criteriaBuilder.equal(root.get("vendorId").as(Long.class), reqVo.getVendorId());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) && StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            // 按照传入参数查找字段
            Predicate predicate = criteriaBuilder.equal(root.get(reqVo.getSearchPageVo().getSearchKey()), reqVo.getSearchPageVo().getSearchValue());
            predicates.add(predicate);
        }
        // 按照审核状态查询
        Predicate predicate;
        if (reqVo.getAuditStatus() == null) {
            predicate = criteriaBuilder.notEqual(root.get("auditStatus").as(ProductAuditStatusEnum.class), ProductAuditStatusEnum.PASS);
        } else {
            predicate = criteriaBuilder.notEqual(root.get("auditStatus").as(ProductAuditStatusEnum.class), reqVo.getAuditStatus());
        }
        predicates.add(predicate);
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
