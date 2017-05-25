package com.biz.gbck.dao.mysql.repository.product;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品Repository
 *
 * Created by david-liu on 2017/04/21 11:25.
 */
public interface ProductRepository extends CommonJpaRepository<Product, Long>, ProductDao, JpaSpecificationExecutor<Product> {

    Product findByProductCode(String productCode);

    List<Product> findByDeleteFlag(Boolean deleteFlag);
}
