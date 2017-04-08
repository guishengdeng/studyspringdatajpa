package com.depotnextdoor.dao.rdb.repository.admin;

import com.depotnextdoor.dao.rdb.po.security.MainMenu;
import com.depotnextdoor.support.jpa.repository.CommonJpaRepository;
import java.util.List;

/**
 * 主菜单Repository
 *
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
public interface MainMenuRepository extends CommonJpaRepository<MainMenu, Long> {
    List<MainMenu> findByOrderByCodeAscNameAsc();
}
