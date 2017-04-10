package com.depotnextdoor.dao.rdb.repository.admin;

import com.depotnextdoor.dao.rdb.po.security.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
