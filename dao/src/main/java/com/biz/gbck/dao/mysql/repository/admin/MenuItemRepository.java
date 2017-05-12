package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
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
public interface MenuItemRepository extends CommonJpaRepository<MenuItem, Long>, MainMenuDao, JpaSpecificationExecutor<MenuItem> {

    @Transactional
    @Modifying
    @Query("UPDATE MenuItem menuItem SET menuItem.status=:status WHERE menuItem.id=:id ")
    void updateStatus(@Param("id") Long id, @Param("status")CommonStatusEnum status);

    List<MenuItem> findByStatus(CommonStatusEnum status);
    @Query("SELECT mi FROM MenuItem mi WHERE mi.mainMenu.id = :id and mi.name = :name")
    MenuItem  finMenuItem(@Param("id") Long id,@Param("name") String name);
}

