package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.dao.mysql.repository.admin.MainMenuRepository;
import com.biz.gbck.dao.mysql.repository.admin.MenuItemRepository;
import com.biz.gbck.vo.menu.MenuItemVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.security.interfaces.MainMenuService;
import com.biz.service.security.interfaces.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuItemServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年04月19日
 * @reviewer
 * @description
 * @see
 */
@Service
public class MenuItemServiceImpl extends AbstractBaseService implements MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MainMenuRepository mainMenuRepository;
    @Override
    public void addOrUpdate(MenuItem menuItem) {

        menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findOne(id);
    }

    @Override
    public List<MenuItem> listMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        menuItemRepository.delete(id);
    }
}