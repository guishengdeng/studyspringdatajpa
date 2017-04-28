package com.biz.gbck.dao.mysql.repository.bbc.productStatistic;

import com.biz.gbck.dao.mysql.po.product.meta.ProductStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * 商品统计数据Repository
 *
 * @author zhangcheng
 * @date 2017/2/21
 * @reviewer
 * @see
 */
//@Repository
//public interface ProductStatisticRepository extends CommonJpaRepository<ProductStatistic, Long>, ProductStatisticDao {
public interface ProductStatisticRepository {

    @Query("FROM ProductStatistic p WHERE p.product.id = ?1")
    ProductStatistic findByProductId(Long productId);

    @Query("FROM ProductStatistic p WHERE p.product.id IN ?1")
    List<ProductStatistic> findByProductIds(List<Long> productIds);
}
