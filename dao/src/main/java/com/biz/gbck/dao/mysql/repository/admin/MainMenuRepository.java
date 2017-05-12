package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface MainMenuRepository extends CommonJpaRepository<MainMenu, Long>, MainMenuDao, JpaSpecificationExecutor<MainMenu> {

    List<MainMenu> findByOrderByCodeAscNameAsc();

    @Query("FROM MainMenu mm WHERE mm.status = ?1 order by mm.code asc")
    List<MainMenu> findByStatus(CommonStatusEnum status);

    @Transactional
    @Modifying
    @Query("UPDATE MainMenu mainMenu SET mainMenu.status = :status WHERE mainMenu.id = :id")
    Integer updateStatus(@Param("id") Long id, @Param("status") CommonStatusEnum status);

    MainMenu findByName(String name);
}

