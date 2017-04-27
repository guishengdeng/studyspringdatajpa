package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends CommonJpaRepository<Admin, String>, MainMenuDao, JpaSpecificationExecutor<Admin> {
    List<Admin> findAdminsByStatus(CommonStatusEnum statusEnum);

    @Transactional
    @Modifying
    @Query("UPDATE Admin admin SET admin.status=:status WHERE admin.username=:username")

    void updateStatus(@Param("status") CommonStatusEnum status,@Param("username") String username);

}
