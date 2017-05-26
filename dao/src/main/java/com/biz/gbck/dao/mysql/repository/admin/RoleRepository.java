package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.Role;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RoleRepository extends CommonJpaRepository<Role, Long>, RoleDao, JpaSpecificationExecutor<Role> {
    @Transactional
    @Modifying
    @Query("UPDATE Role role SET role.status=:status WHERE role.id=:id")
    void  updateStatus(@Param("id") Long id, @Param("status") CommonStatusEnum statusEnum);

    List<Role> getByStatus(CommonStatusEnum status);

    @Query("FROM Role role WHERE role.name = :name")
    Role  getRoleCondition(@Param("name") String roleName);

}
