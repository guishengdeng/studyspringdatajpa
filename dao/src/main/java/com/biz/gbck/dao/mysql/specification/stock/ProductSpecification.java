package com.biz.gbck.dao.mysql.specification.stock;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.vo.product.SearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by lzz on 2017/5/17.
 */
public class ProductSpecification implements Specification<Product> {

    private SearchVo productShowVo;

    public ProductSpecification(SearchVo productVo) {
        this.productShowVo = productVo;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();

        if (StringUtils.isNotBlank(productShowVo.getCategoryName())){
            Predicate predicate = criteriaBuilder.like(root.join("category").get("name"), "%" + productShowVo.getCategoryName().trim()+ "%");
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(productShowVo.getBrandName())){
           Predicate predicate = criteriaBuilder.like(root.join("brand").get("name").as(String.class), "%" + productShowVo.getBrandName().trim() + "%" );
           predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(productShowVo.getProductCode())){
            Predicate predicate = criteriaBuilder.like(root.get("productCode").as(String.class), "%" +productShowVo.getProductCode().trim() + "%");
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(productShowVo.getName())){
            String sqlName = "%" + productShowVo.getName().trim() + "%";
            predicates.add(criteriaBuilder.like(root.get("name").as(String.class),sqlName));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
