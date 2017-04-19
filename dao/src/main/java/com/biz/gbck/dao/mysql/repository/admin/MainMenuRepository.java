package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.support.jpa.repository.CommonJpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface MainMenuRepository extends CommonJpaRepository<MainMenu, Long>, MainMenuDao, JpaSpecificationExecutor<MainMenu> {

    List<MainMenu> findByOrderByCodeAscNameAsc();

}

