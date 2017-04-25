package com.biz.gbck.dao.mysql.repository.category;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author 江南
 * @usage Category Repository
 * @reviewer
 * @since 2016/12/19
 */
@Repository
public interface CategoryRepository extends CommonJpaRepository<Category, Long>, JpaSpecificationExecutor<Category>, CategoryDao {

    List<Category> findByIdInAndStatusAndDeleteFlag(Iterable<Long> ids, Integer status, Boolean deleteFlag);

    Category findByIdAndStatus(Long id, Integer status);

    Category findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

    List<Category> findByParentIsNullAndSeoKeywordsLikeAndDeleteFlagOrderByIdx(String keyWords, Boolean deleteFlag);

    List<Category> findByParentIsNullAndDeleteFlagOrderByIdx(Boolean deleteFlag);

    @Query("SELECT MAX(c.idx) FROM Category c where c.parent.id = ?1")
    Integer findMaxIdx(Long id);

    @Query("FROM Category c where c.parent.id = ?1 AND c.seoKeywords like %?2% AND c.deleteFlag = ?3 ORDER BY c.idx asc")
    List<Category> findByParentIdAndSeoKeywordsLikeAndDeleteFlag(Long parentCategoryId, String keyWords, Boolean deleteFlag);

    List<Category> findByIdAndDeleteFlag(List<Long> ids, Boolean deleteFlag);

    Page<Category> findByNameLikeAndDeleteFlag(String name, Boolean deleteFlag, Pageable pageable);

    List<Category> findByDeleteFlag(Boolean deleteFlag);

    Category findByName(String name);

    List<Category> findCategoryByName(String name);
}
