package com.biz.gbck.dao.mysql.repository.bbc.product;

import com.biz.gbck.dao.mysql.po.product.GroupProduct;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 组合商品Repository
 *
 * @author zhangcheng
 * @date 2017/3/13
 * @reviewer
 * @see
 */
@Repository
public interface GroupProductRepository extends CommonJpaRepository<GroupProduct, Long>, GeoProductDao {

    @Query("FROM GroupProduct g WHERE g.product.id = ?1")
    GroupProduct findByProductId(Long productId);
}
