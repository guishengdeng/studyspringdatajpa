package com.biz.gbck.dao.mysql.specification;

import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.vo.product.backend.GeoProductReqVo;
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
 * @since 2017/2/13
 */
public class PutawayGeoProductSpecification implements Specification<GeoProduct> {

    private GeoProductReqVo reqVo;

    public PutawayGeoProductSpecification(GeoProductReqVo reqVo) {
        this.reqVo = reqVo;
    }

    @Override
    public Predicate toPredicate(Root<GeoProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTimestamp")));
        // 查询未删除的
        predicates.add(criteriaBuilder.equal(root.get("saleStatus").as(SaleStatusEnum.class), SaleStatusEnum.ON_SALE));
        if (reqVo.getCategoryId() != null) {
            // 商家ID
            Predicate predicate = criteriaBuilder.equal(root.get("product").get("category").get("id").as(Long.class), reqVo.getCategoryId());
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) && StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            // 按照传入参数查找字段
            Predicate predicate = criteriaBuilder.equal(root.get("product").get(reqVo.getSearchPageVo().getSearchKey()), reqVo.getSearchPageVo().getSearchValue());
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();

    }

}
