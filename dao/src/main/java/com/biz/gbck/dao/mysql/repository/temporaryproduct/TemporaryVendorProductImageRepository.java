package com.biz.gbck.dao.mysql.repository.temporaryproduct;

import com.biz.gbck.dao.mysql.po.product.bbc.TemporaryVendorProductImage;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangcheng
 * @date 2017/2/22
 * @reviewer
 * @see
 */
@Repository
public interface TemporaryVendorProductImageRepository extends CommonJpaRepository<TemporaryVendorProductImage, Long>, TemporaryVendorProductImageDao {
}
