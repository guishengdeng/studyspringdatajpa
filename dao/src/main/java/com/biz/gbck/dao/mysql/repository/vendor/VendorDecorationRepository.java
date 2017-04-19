package com.biz.gbck.dao.mysql.repository.vendor;

import com.biz.gbck.dao.mysql.po.vendor.VendorDecoration;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 店铺装饰Repository
 *
 * @author LGJ
 */

@Repository
public interface VendorDecorationRepository extends CommonJpaRepository<VendorDecoration, Long>, JpaSpecificationExecutor<VendorDecoration>, VendorDecorationDao {

    VendorDecoration findByVendorId(Long vendorId);
}
