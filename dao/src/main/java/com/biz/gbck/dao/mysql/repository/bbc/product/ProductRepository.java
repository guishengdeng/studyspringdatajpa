package com.biz.gbck.dao.mysql.repository.bbc.product;

import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.enums.product.VendorTypeEnum;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * 商品 Repository
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
//public interface ProductRepository extends CommonJpaRepository<Product, Long>, ProductDao, JpaSpecificationExecutor<Product> {
public interface ProductRepository {

    Page<Product> findByVendorIdAndDeleteFlag(Long vendorId, Boolean deleteFlag, Pageable pageable);

    Product findByIdAndVendorIdAndDeleteFlag(Long productId, Long vendorId, Boolean deleteFlag);

    Product findByIdAndVendorId(Long productId, Long vendorId);

    List<Product> findByIdInAndVendorIdAndDeleteFlag(Collection<Long> ids, Long vendorId, Boolean deleteFlag);

    List<Product> findByIdInAndVendorId(Collection<Long> ids, Long vendorId);

    List<Product> findByIdAndDeleteFlag(List<Long> ids, Boolean aFalse);

    Page<Product> findByProductCodeLikeAndDeleteFlag(String searchKey, Boolean deleteFlag, Pageable pageable);

    Page<Product> findByNameLikeAndDeleteFlag(String name, Boolean deleteFlag, Pageable pageable);

    @Query("select p.id from Product p where p.productCode = ?1")
    Long findProductIdByProductCode(String productCode);

    @Query("select p.productCode from Product p where p.id = ?1")
    String findProductCodeById(Long aLong);

    List<Product> findByProductCodeInAndVendorId(List<String> productCode, Long vendorId);

    Product findByProductCode(String productCode);

    List<Long> findProductIdByNameLike(String name);

    List<Product> findByProductType(VendorTypeEnum type);

    List<Product> findByProductTypeAndDeleteFlag(VendorTypeEnum type, Boolean deleteFlag);

    List<Product> findByProductCodeIn(List<String> blackListProductCodes);

    List<Product> findByIsRapidProduct(Boolean isRapidProduct);
}
