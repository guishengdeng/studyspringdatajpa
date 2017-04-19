package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.repository.admin.MainMenuRepository;
import com.biz.service.AbstractBaseService;
import com.biz.service.security.interfaces.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MainMenuServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年04月18日
 * @reviewer
 * @description
 * @see
 */
@Service
public class MainMenuServiceImpl extends AbstractBaseService implements MainMenuService {
    @Autowired
    private MainMenuRepository mainMenuRepository;
    @Override
    public List<MainMenu> listMainMenus() {
        return mainMenuRepository.findByOrderByCodeAscNameAsc();
    }

    @Override
    public MainMenu getMainMenu(Long id) {
        return mainMenuRepository.findOne(id);
    }
}