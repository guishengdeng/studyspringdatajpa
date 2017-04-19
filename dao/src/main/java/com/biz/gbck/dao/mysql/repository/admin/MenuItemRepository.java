package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CommonJpaRepository<MenuItem, Long>, MainMenuDao, JpaSpecificationExecutor<MenuItem> {

}

