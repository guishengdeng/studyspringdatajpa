package com.biz.gbck.dao.mysql.specification.bbc;

import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.gbck.enums.product.SaleStatusEnum;
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
 * 区域商品 JPA Specification
 *
 * @author david-liu
 * @date 2017年01月16日
 * @reviewer
 * @see
 */
public class GeoProductSpecification implements Specification<GeoProduct> {

    private VendorProductListReqVo reqVo;

    public GeoProductSpecification(VendorProductListReqVo reqVo) {
        this.reqVo = reqVo;
    }

    @Override
    public Predicate toPredicate(Root<GeoProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTimestamp")));
        // 查询未删除的
        predicates.add(criteriaBuilder.equal(root.get("product").get("deleteFlag").as(Boolean.class), Boolean.FALSE));
        if (reqVo.getVendorId() != null) {
            // 商家ID
            Predicate predicate = criteriaBuilder.equal(root.get("product").get("vendorId").as(Long.class), reqVo.getVendorId());
            predicates.add(predicate);
        }

        if (reqVo.getSaleStatus() != null) {
            // 上下架状态
            Predicate predicate = criteriaBuilder.equal(root.get("saleStatus").as(SaleStatusEnum.class), reqVo.getSaleStatus());
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) && StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            // 按照传入参数查找字段
            Predicate predicate = criteriaBuilder.equal(root.get("product").get(reqVo.getSearchPageVo().getSearchKey()), reqVo.getSearchPageVo().getSearchValue());
            predicates.add(predicate);
        }

        if (reqVo.getAuditStatus() != null) {
            // 按照审核状态查询
            Predicate predicate = criteriaBuilder.equal(root.get("productAudit").get("auditStatus").as(ProductAuditStatusEnum.class), reqVo.getAuditStatus());
            predicates.add(predicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
