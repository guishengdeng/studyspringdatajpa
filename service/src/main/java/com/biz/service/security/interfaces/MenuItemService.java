package com.biz.service.security.interfaces;

import com.biz.gbck.dao.mysql.po.security.MainMenu;
import com.biz.gbck.dao.mysql.po.security.MenuItem;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.menu.UpdateMenuItemParentMenuReqVo;

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

     List<MenuItem> listByStatus(CommonStatusEnum status);

     Boolean  isExist(MenuItem mm);

     /**
      * 修改子菜单所属的父级菜单
      *
      * 思路：就是把子菜单原属的父级菜单替换成用户所选中的父级菜单
      */
     void updateMenuItemParentMenu(UpdateMenuItemParentMenuReqVo vo);
}
