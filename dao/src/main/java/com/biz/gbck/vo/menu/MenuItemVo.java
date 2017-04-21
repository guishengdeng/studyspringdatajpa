package com.biz.gbck.vo.menu;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * MenuItemVo
 *
 * @author guisheng.deng
 * @date 2017年04月19日
 * @reviewer
 * @description:用于映射子菜单修改和修改时传递到后台的参数
 * @see
 */
public class MenuItemVo implements Serializable {
    private Long id;
    private Integer code;
    private String name;
    private String icon ;
    private String link;
    private String description;
    private String symbol;

    public Long getMainmenu_id() {
        return mainmenu_id;
    }

    public void setMainmenu_id(Long mainmenu_id) {
        this.mainmenu_id = mainmenu_id;
    }

    private Long mainmenu_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


}