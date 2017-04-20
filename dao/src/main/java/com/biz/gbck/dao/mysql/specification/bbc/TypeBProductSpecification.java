package com.biz.gbck.dao.mysql.specification.bbc;

import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.product.backend.TypeProductListReqVo;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
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
 * @since 2017/2/10
 */
public class TypeBProductSpecification implements Specification<Product> {

    private TypeProductListReqVo reqVo;

    public TypeBProductSpecification(TypeProductListReqVo reqVo) {
        this.reqVo = reqVo;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        query.orderBy(cb.desc(root.get("createTimestamp")));
        // 查询未删除的
        predicates.add(cb.equal(root.get("deleteFlag").as(Boolean.class), Boolean.FALSE));

        predicates.add(cb.equal(root.get("productType").as(VendorTypeEnum.class), VendorTypeEnum.TYPE_B));

        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) && StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            // 按照传入参数查找字段
            Predicate predicate;
            if (Objects.equals(reqVo.getSearchPageVo().getSearchKey(), "name")) {
                predicate = cb.like(root.get(reqVo.getSearchPageVo().getSearchKey()).as(String.class), "%" + reqVo.getSearchPageVo().getSearchValue() + "%");
            } else {
                predicate = cb.equal(root.get(reqVo.getSearchPageVo().getSearchKey()), reqVo.getSearchPageVo().getSearchValue());
            }
            predicates.add(predicate);
        }
        if (reqVo.getCategoryId() != null) {
            Predicate predicate = cb.equal(root.get("category").get("id"), reqVo.getCategoryId());
            predicates.add(predicate);
        }


        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return query.getRestriction();
    }
}
