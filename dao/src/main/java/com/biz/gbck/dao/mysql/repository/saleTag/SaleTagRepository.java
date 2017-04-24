package com.biz.gbck.dao.mysql.repository.saleTag;

import com.biz.gbck.dao.mysql.po.product.SaleTag;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 销售标签 Repository
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
@Repository
public interface SaleTagRepository extends CommonJpaRepository<SaleTag, Long>, JpaSpecificationExecutor<SaleTag>, SaleTagDao {

    SaleTag findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

    List<SaleTag> findByCategoryIdAndDeleteFlag(Long id, boolean flag);

    List<SaleTag> findByNameAndDeleteFlag(String searchValue, Boolean aFalse);

    List<SaleTag> findByDeleteFlag(Boolean aFalse);
}
