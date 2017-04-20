package com.biz.gbck.dao.mysql.repository.bbc.temporaryproduct;

import com.biz.gbck.dao.mysql.po.product.bbc.TemporaryVendorProduct;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/2/22
 * @reviewer
 * @see
 */
@Repository
public interface TemporaryVendorProductRepository extends CommonJpaRepository<TemporaryVendorProduct, Long>, TemporaryVendorProductDao {

    @Query("FROM TemporaryVendorProduct p WHERE p.flavor IS NOT NUll AND p.flavor != :flavor")
    List<TemporaryVendorProduct> findByFlavor(@Param("flavor") String flavor);

    @Query("FROM TemporaryVendorProduct p WHERE p.country IS NOT NUll AND p.country != :country")
    List<TemporaryVendorProduct> findByCountry(@Param("country") String country);

    List<TemporaryVendorProduct> findByNetContentIsNotNull();

    @Query("FROM TemporaryVendorProduct p WHERE p.standard IS NOT NUll AND p.standard != :standard")
    List<TemporaryVendorProduct> findByStandard(@Param("standard") String standard);

    List<TemporaryVendorProduct> findByProductCodeIn(List<String> productCodes);
}
