package com.biz.gbck.dao.mysql.repository.temporaryproduct;

import com.biz.gbck.dao.mysql.po.product.TemporaryGroupProductItem;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/3/15
 * @reviewer
 * @see
 */
@Repository
public interface TemporaryGroupProductItemRepository extends CommonJpaRepository<TemporaryGroupProductItem, Long>, TemporaryGroupProductItemDao {
}
