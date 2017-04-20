package com.biz.gbck.dao.mysql.repository.temporaryproduct;

import com.biz.gbck.dao.mysql.po.product.bbc.TemporaryProduct;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/2/19
 * @reviewer
 * @see
 */
@Repository
public interface TemporaryProductRepository extends CommonJpaRepository<TemporaryProduct, Long>, TemporaryProductDao {

    List<TemporaryProduct> findByFlavorIsNotNull();

    @Query("FROM TemporaryProduct p WHERE p.country IS NOT NUll AND p.country != :country")
    List<TemporaryProduct> findByCountry(@Param("country") String country);

    List<TemporaryProduct> findByAlcIsNotNull();

    List<TemporaryProduct> findByWortIsNotNull();

    List<TemporaryProduct> findByShelfLifeIsNotNull();

    @Query("FROM TemporaryProduct p WHERE p.material IS NOT NUll AND p.material != :material")
    List<TemporaryProduct> findByMaterial(@Param("material") String material);

    @Query("FROM TemporaryProduct p WHERE p.craft IS NOT NUll AND p.craft != :craft")
    List<TemporaryProduct> findByCraft(@Param("craft") String craft);

    @Query("FROM TemporaryProduct p WHERE p.origin IS NOT NUll AND p.origin != :origin")
    List<TemporaryProduct> findByOrigin(@Param("origin") String origin);

    List<TemporaryProduct> findByYearIsNotNull();

    List<TemporaryProduct> findByNetContentIsNotNull();

    @Query("FROM TemporaryProduct p WHERE p.standard IS NOT NUll AND p.standard != :standard")
    List<TemporaryProduct> findByStandard(@Param("standard") String standard);
}
