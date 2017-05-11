package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.Resource;
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
public interface ResourceRepository extends CommonJpaRepository<Resource, Long>, MainMenuDao, JpaSpecificationExecutor<Resource> {

    @Transactional
    @Modifying
    @Query("UPDATE Resource resource SET resource.status=:status WHERE resource.id=:id")
    void  updateStatus(@Param("id") Long id,@Param("status") CommonStatusEnum statusEnum);

    List<Resource> getByStatus(CommonStatusEnum status);

    Resource findByMenuItemIdAndName(Long id,String name);
}

