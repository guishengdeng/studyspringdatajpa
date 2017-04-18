package com.biz.gbck.dao.mysql.repository.productExtend;

import com.biz.gbck.dao.mysql.po.product.ProductExtend;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 商品扩展属性 Repository
 *
 * @author wangyumin
 * @date 2016年12月29日
 * @reviewer
 * @see
 */
@Repository
public interface ProductExtendRepository extends CommonJpaRepository<ProductExtend, Long>, JpaSpecificationExecutor<ProductExtend>, ProductExtendDao {

    List<ProductExtend> findByIdIn(Collection<Long> ids);

    ProductExtend findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    @Query("FROM ProductExtend pe Where pe.category.id = ?1 AND pe.name like %?2% AND pe.deleteFlag = ?3 ORDER BY pe.idx")
    List<ProductExtend> findByCategoryIdAndNameLikeAndDeleteFlag(Long categoryId, String name, boolean deleteFlag);

    @Query("SELECT MAX(pe.idx) FROM ProductExtend pe where pe.category.id = ?1")
    Integer findMaxIdx(Long categoryId);

    List<ProductExtend> findByCategoryId(Long categoryId);
}