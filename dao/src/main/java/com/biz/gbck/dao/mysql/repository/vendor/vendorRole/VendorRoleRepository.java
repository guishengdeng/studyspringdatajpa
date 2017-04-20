package com.biz.gbck.dao.mysql.repository.vendor.vendorRole;

import com.biz.gbck.dao.mysql.po.vendor.bbc.VendorRole;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorRoleRepository extends CommonJpaRepository<VendorRole, Long>, JpaSpecificationExecutor<VendorRole> {

    VendorRole findByName(String name);
}
