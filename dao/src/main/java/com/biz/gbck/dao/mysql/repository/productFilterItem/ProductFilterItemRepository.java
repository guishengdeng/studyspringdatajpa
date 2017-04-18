package com.biz.gbck.dao.mysql.repository.productFilterItem;

import com.biz.gbck.dao.mysql.po.product.ProductFilterItem;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author wangyumin
 * @date 2017年1月5日
 */
@Repository
public interface ProductFilterItemRepository extends CommonJpaRepository<ProductFilterItem, Long>, JpaSpecificationExecutor<ProductFilterItem>, ProductFilterItemDao {

    @Query("SELECT MAX(pfi.idx) FROM ProductFilterItem pfi where pfi.productFilter.id = ?1")
    Integer findMaxIdx(Long ProductFilterId);

    @Query("FROM ProductFilterItem pfi where pfi.productFilter.id = ?1 AND pfi.deleteFlag = ?2 ORDER BY pfi.idx")
    List<ProductFilterItem> findByProductFilterIdAndDeleteFlag(Long productFilterId, Boolean deleteFlag);
}
