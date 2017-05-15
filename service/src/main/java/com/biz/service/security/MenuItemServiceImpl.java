package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.dao.mysql.repository.admin.MainMenuRepository;
import com.biz.gbck.dao.mysql.repository.admin.MenuItemRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.menu.UpdateMenuItemParentMenuReqVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.security.interfaces.MenuItemService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<MenuItem> listByStatus(CommonStatusEnum status) {
        return menuItemRepository.findByStatus(status);
    }

    @Override
    public Boolean isExist(MenuItem mm) {
        MenuItem menuItem = menuItemRepository.finMenuItem(mm.getMainMenu().getId(), mm.getName().trim());
           if (menuItem != null) {
                if( mm.getId() != null && mm.getId().equals(menuItem.getId())){
                    return Boolean.TRUE;
                }
            return Boolean.FALSE;
         }
        return Boolean.TRUE;
    }

    /**
     * 修改子菜单所属的父级菜单
     *
     */
    @Override
    @Transactional
    public void updateMenuItemParentMenu(UpdateMenuItemParentMenuReqVo vo) {
        if (vo != null) {
            List<MenuItem> menuItems = new ArrayList<MenuItem>();
            List<Long> menuItemIds = Lists.newArrayList();
            Map<Long, Long> menuItemIdsAndMainMenuIds = new HashMap<Long, Long>();
            if (vo.getMenuItemIds() != null) {
                //用当前集合的下标去获取另外一个集合对应的元素
                for (int index = 0; index < vo.getMenuItemIds().size(); index++) {
                    Long menuItemId = vo.getMenuItemIds().get(index);
                    menuItemIds.add(menuItemId);
                    Long mainMenuId = vo.getMenuItemParentIds().get(index);
                    menuItemIdsAndMainMenuIds.put(menuItemId, mainMenuId == null ? menuItemRepository.findOne(menuItemId).getMainMenu().getId() : mainMenuId);
                }
                menuItems = menuItemRepository.findAll(menuItemIds);
                for (MenuItem menuItem : menuItems) {
                    menuItem.setMainMenu(mainMenuRepository.findOne(menuItemIdsAndMainMenuIds.get(menuItem.getId())));
                }
                menuItemRepository.save(menuItems);
            }
        }

    }

    @Override
    public void delete(Long id) {
        menuItemRepository.updateStatus(id, CommonStatusEnum.DISABLE);
    }
}