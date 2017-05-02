package com.biz.gbck.dao.mysql.repository.product;

import com.biz.gbck.dao.mysql.po.product.meta.ProductStatistic;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * 商品统计Repository
 *
 * Created by david-liu on 2017/04/24 12:47.
 */
public interface ProductStatisticRepository extends CommonJpaRepository<ProductStatistic, Long>, ProductStatisticDao {
    @Query("FROM ProductStatistic p WHERE p.product.id = ?1")
    ProductStatistic findByProductId(Long productId);

    @Query("FROM ProductStatistic p WHERE p.product.id IN ?1")
    List<ProductStatistic> findByProductIds(List<Long> productIds);
}
