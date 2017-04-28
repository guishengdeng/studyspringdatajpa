package com.biz.gbck.dao.mysql.repository.productFilter;

import com.biz.gbck.dao.mysql.po.product.meta.ProductFilter;
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
public interface ProductFilterRepository extends CommonJpaRepository<ProductFilter, Long>, JpaSpecificationExecutor<ProductFilter>, ProductFilterDao {

    ProductFilter findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

    @Query("FROM ProductFilter pf where pf.category.id = ?1 AND pf.label like %?2% AND pf.deleteFlag = ?3 ORDER BY pf.idx")
    List<ProductFilter> findByCategoryIdAndLabelLikeAndDeleteFlag(Long categoryId, String label, Boolean deleteFlag);

    @Query("SELECT MAX(pf.idx) FROM ProductFilter pf where pf.category.id = ?1")
    Integer findMaxIdx(Long categoryId);

}
