package com.biz.gbck.dao.mysql.repository.brand;

import com.biz.gbck.dao.mysql.po.product.Brand;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 品牌 Repository
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/19
 */
@Repository
public interface BrandRepository extends CommonJpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand>, BrandDao {
    Brand findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

    Page<Brand> findByCategoriesIdInAndNameLikeAndDeleteFlag(Collection<Long> categoryIds, String brandName, Boolean deleteFlag, Pageable pageRequest);

    Page<Brand> findByCategoriesIdAndNameLikeAndDeleteFlag(Long categoryId, String brandName, Boolean deleteFlag, Pageable pageRequest);

    Page<Brand> findByDeleteFlag(Boolean deleteFlag, Pageable pageable);

    List<Brand> findByDeleteFlagAndIdIn(Boolean deleteFlag, List<Long> ids);
    
    

    Page<Brand> findByNameLikeAndDeleteFlag(String name, Boolean deleteFlag, Pageable pageable);

    Page<Brand> findByDeleteFlagAndNameLike(Boolean aFalse, String s, Pageable pageRequest);

    @Query("select b from Brand b join b.categories c where c.id = ?1 and b.deleteFlag = ?2")
    List<Brand> findBycategoryIdAndDeleteFlag(Long categoryId, Boolean aFalse);
    
//    @Query("select b from Brand b join b.categories bc where bc.id = :categoryId and b.deleteFlag=false order by rand() ")

    List<Brand> findByName(String name);

    @Query("FROM Brand GROUP BY name")
    List<Brand> findGroupByname();
//    @Query("select new com.biz.soa.vo.vendor.BrandStreetVo() from Brand b ")
//    List<Brand> findByDeleteFlag(@Param("categoryId") Long id, Pageable pageable);
}
