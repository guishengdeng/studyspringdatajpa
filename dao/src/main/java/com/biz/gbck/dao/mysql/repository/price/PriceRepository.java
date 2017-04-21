package com.biz.gbck.dao.mysql.repository.price;

import com.biz.gbck.dao.mysql.po.product.price.Price;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by david-liu on 2017/04/21 11:36.
 */
public interface PriceRepository extends CommonJpaRepository<Price, Long>,
        PriceDao, JpaSpecificationExecutor<Price> {
}
