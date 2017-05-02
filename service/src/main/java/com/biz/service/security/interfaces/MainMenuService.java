package com.biz.service.security.interfaces;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.enums.CommonStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MainMenuService
 *
 * @author guisheng.deng
 * @date 2017年04月18日
 * @reviewer
 * @description
 * @see
 */

public interface MainMenuService {
      List<MainMenu> listMainMenus();

      MainMenu getMainMenu(Long id);

      void addOrUpdate(MainMenu mainMenu);

      void delete(Long id);

      List<MainMenu> listByStatus(CommonStatusEnum status);

}
