package com.spring.jpa.service;

import com.spring.jpa.model.MenuItem;
import com.spring.jpa.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MenuItemService
 *
 * @author guisheng.deng
 * @date 2017年04月01日
 * @reviewer
 * @description
 * @see
 */
@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public Iterable<MenuItem> getMenuItems(){

        return menuItemRepository.findAll();
    }
    public MenuItem getMenuItemById(Long id){
        return menuItemRepository.findOne(id);
    }
    public void addOrUpdate(MenuItem menuItem){
        menuItemRepository.save(menuItem);
    }

    public void delete(Long id){
        menuItemRepository.delete(id);
    }
}