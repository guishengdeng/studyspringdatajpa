package com.biz.gbck.dao.mysql.specification.stock;

import com.biz.gbck.dao.mysql.po.stock.Stock;
import com.biz.gbck.vo.stock.StockShowVo;
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
public class StockSpecification implements Specification<Stock> {

    private StockShowVo stockShowVo;

    public StockSpecification(StockShowVo stockShowVo) {
        this.stockShowVo = stockShowVo;
    }

    @Override
    public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();

        if (stockShowVo.getProductId() != null){
            Predicate predicate = criteriaBuilder.equal(root.get("productId"),stockShowVo.getProductId());
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(stockShowVo.getCompanyName())){
            String sqlName = "%" + stockShowVo.getCompanyName().trim() + "%";
            predicates.add(criteriaBuilder.like(root.get("companyName").as(String.class),sqlName));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
}
