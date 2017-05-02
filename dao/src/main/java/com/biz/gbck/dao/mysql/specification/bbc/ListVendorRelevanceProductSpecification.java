package com.biz.gbck.dao.mysql.specification.bbc;

import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.dao.mysql.po.product.bbc.RelevantProduct;
import com.biz.gbck.vo.product.backend.ListVendorRelevanceProductReqVo;
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
 * @since 2017/2/25
 */
public class ListVendorRelevanceProductSpecification implements Specification<Product> {

    private ListVendorRelevanceProductReqVo reqVo;

    public ListVendorRelevanceProductSpecification(ListVendorRelevanceProductReqVo reqVo) {
        this.reqVo = reqVo;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) &&
                StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            // 按照传入参数查找字段
            Predicate predicate = criteriaBuilder.equal(root.get(reqVo.getSearchPageVo().getSearchKey()),
                    reqVo.getSearchPageVo().getSearchValue());
            predicates.add(predicate);
        }
        predicates.add(criteriaBuilder.isNotNull(root.get("relevanceProduct").as(RelevantProduct.class)));
        // 按照审核状态查询
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
