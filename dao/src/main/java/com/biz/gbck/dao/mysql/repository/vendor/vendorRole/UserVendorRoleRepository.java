package com.biz.gbck.dao.mysql.repository.vendor.vendorRole;

import com.biz.gbck.dao.mysql.po.vendor.bbc.UserVendorRole;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserVendorRoleRepository extends CommonJpaRepository<UserVendorRole, Long>, JpaSpecificationExecutor<UserVendorRole> {

}
