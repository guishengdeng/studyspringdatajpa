package com.biz.gbck.dao.mysql.specification;

import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.vo.product.backend.BackendProductListReqVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/7
 */
public class ProductListSpecification implements Specification<Product> {

    private BackendProductListReqVo reqVo;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTimestamp")));

        // 删除标识
        predicates.add(criteriaBuilder.equal(root.get("deleteFlag").as(Boolean.class), Boolean.FALSE));

        //过滤vendor
        predicates.add(criteriaBuilder.equal(root.get("vendorId").as(Long.class), reqVo.getVendorId()));

        //添加过滤条件,排除vendorName过滤条件  后面过滤
        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) && StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            Expression<String> expression = null;
            // 按照传入参数查找字段
            if (StringUtils.equals(reqVo.getSearchPageVo().getSearchKey(), "productName")) {
                expression = root.get("name");
            }
            if (StringUtils.equals(reqVo.getSearchPageVo().getSearchKey(), "productCode")) {
                expression = root.get("productCode");
            }
            Predicate predicate = criteriaBuilder.like(expression, "%" + reqVo.getSearchPageVo().getSearchValue() + "%");
            predicates.add(predicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }


    public ProductListSpecification(BackendProductListReqVo backendProductListReqVo) {
        this.reqVo = backendProductListReqVo;
    }
}
