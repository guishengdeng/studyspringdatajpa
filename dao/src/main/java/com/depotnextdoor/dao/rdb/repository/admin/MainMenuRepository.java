package com.depotnextdoor.dao.rdb.repository.admin;

import com.depotnextdoor.dao.rdb.po.security.MainMenu;
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
