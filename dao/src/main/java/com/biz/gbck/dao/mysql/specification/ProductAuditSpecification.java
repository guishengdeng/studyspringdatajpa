package com.biz.gbck.dao.mysql.specification;

import com.biz.gbck.dao.mysql.po.product.ProductAudit;
import com.biz.gbck.vo.product.backend.PlatformProductAuditListReqVo;
import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author 江南
 * @date 2017/1/19
 * @reviewer
 */
public class ProductAuditSpecification implements Specification<ProductAudit> {

    private PlatformProductAuditListReqVo reqVo;

    @Override
    public Predicate toPredicate(Root<ProductAudit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();
        // 按照创建时间倒序
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTimestamp")));

        if (reqVo.getCategoryId() != null) {
            // 分类ID
            Predicate predicate = criteriaBuilder.equal(root.get("category").get("id").as(Long.class), reqVo.getCategoryId());
            predicates.add(predicate);
        }

        if (reqVo.getAuditStatus() != null) {
            // 审核状态
            Predicate predicate = criteriaBuilder.equal(root.get("auditStatus").as(String.class), reqVo.getAuditStatus().toString());
            predicates.add(predicate);
        }
        //添加过滤条件,排除vendorName过滤条件  后面过滤
        if (StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchKey()) && StringUtils.isNotBlank(reqVo.getSearchPageVo().getSearchValue())) {
            Expression<String> expression = null;
            // 按照传入参数查找字段
            if (StringUtils.equals(reqVo.getSearchPageVo().getSearchKey(), "brandName")) {
                expression = root.get("brand").get("name");
            }
            if (StringUtils.equals(reqVo.getSearchPageVo().getSearchKey(), "productName")) {
                expression = root.get("name");
            }
            if (StringUtils.equals(reqVo.getSearchPageVo().getSearchKey(), "vendorName")) {
                expression = root.get("vendorName");
            }
            Predicate predicate = criteriaBuilder.like(expression, "%" + reqVo.getSearchPageVo().getSearchValue() + "%");
            predicates.add(predicate);

        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();

    }

    public ProductAuditSpecification(PlatformProductAuditListReqVo reqVo) {
        this.reqVo = reqVo;
    }
}
