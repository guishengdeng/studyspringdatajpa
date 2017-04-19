package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminRepository extends CommonJpaRepository<Admin, String>, MainMenuDao, JpaSpecificationExecutor<Admin> {
    List<Admin> findAdminsByStatus(CommonStatusEnum statusEnum);
}
