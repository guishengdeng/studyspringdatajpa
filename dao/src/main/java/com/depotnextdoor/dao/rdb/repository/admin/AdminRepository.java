package com.depotnextdoor.dao.rdb.repository.admin;

import com.depotnextdoor.dao.rdb.po.security.Admin;
import com.depotnextdoor.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CommonJpaRepository<Admin, String> {
}
