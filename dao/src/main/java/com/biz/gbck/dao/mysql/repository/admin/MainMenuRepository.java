package com.biz.gbck.dao.mysql.repository.admin;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 主菜单Repository
 *
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
public interface MainMenuRepository extends JpaRepository<MainMenu, Long> {
    List<MainMenu> findByOrderByCodeAscNameAsc();
}
