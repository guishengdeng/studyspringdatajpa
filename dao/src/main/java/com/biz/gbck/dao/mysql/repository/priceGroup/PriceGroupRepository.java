package com.biz.gbck.dao.mysql.repository.priceGroup;

import com.biz.gbck.dao.mysql.po.product.price.PriceGroup;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品价格组Repository
 *
 * Created by david-liu on 2017/04/21 11:28.
 */
public interface PriceGroupRepository extends CommonJpaRepository<PriceGroup, Long>, PriceGroupDao, JpaSpecificationExecutor<PriceGroup> {
}
