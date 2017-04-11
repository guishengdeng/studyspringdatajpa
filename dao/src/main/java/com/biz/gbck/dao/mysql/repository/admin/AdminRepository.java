package com.biz.gbck.dao.mysql.repository.admin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.support.jpa.repository.CommonJpaRepository;

public interface AdminRepository  extends CommonJpaRepository<Admin, String>, MainMenuDao,JpaSpecificationExecutor<Admin> {

}
