package com.biz.gbck.vo.menu;

import java.io.Serializable;
import java.util.List;

/**
 * UpdateMenuItemParentMenuReqVo
 *
 * @author guisheng.deng
 * @date 2017年04月19日
 * @reviewer
 * @description:用于接收更改子菜单父级菜单的相关参数
 * @lable:前段向后端传递参数时，只能自定义vo来接收参数,不能使用po
 * 接收参数
 */
public class UpdateMenuItemParentMenuReqVo implements Serializable {

    private List<Long> menuItemIds;
    private List<Long> menuItemParentIds;
    public List<Long> getMenuItemIds() {
        return menuItemIds;
    }

    public void setMenuItemIds(List<Long> menuItemIds) {
        this.menuItemIds = menuItemIds;
    }

    public List<Long> getMenuItemParentIds() {
        return menuItemParentIds;
    }

    public void setMenuItemParentIds(List<Long> menuItemParentIds) {
        this.menuItemParentIds = menuItemParentIds;
    }

    public UpdateMenuItemParentMenuReqVo(){

    }

}