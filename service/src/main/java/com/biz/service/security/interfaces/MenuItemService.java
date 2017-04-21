package com.biz.service.security.interfaces;

import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.vo.menu.MenuItemVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuItemService
 *
 * @author guisheng.deng
 * @date 2017年04月19日
 * @reviewer
 * @description
 * @see
 */

public interface MenuItemService {
     void  addOrUpdate(MenuItem menuItem);
     void  delete(Long id);
     MenuItem getMenuItem(Long id);
     List<MenuItem> listMenuItems();
}
