package com.biz.gbck.dao.mysql.repository.bbc.vendor.vendorBrand;

import com.biz.gbck.dao.mysql.po.vendor.bbc.RegisterVendorBrand;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterVendorBrandRepository extends CommonJpaRepository<RegisterVendorBrand, Long>, JpaSpecificationExecutor<RegisterVendorBrand> {

    List<RegisterVendorBrand> findRegisterVendorBrandByAuditVendorId(Long auditVendorId);

}
