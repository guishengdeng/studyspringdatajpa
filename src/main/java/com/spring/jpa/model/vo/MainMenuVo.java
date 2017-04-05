package com.spring.jpa.model.vo;

/**
 * MainMenuVo
 *
 * @author guisheng.deng
 * @date 2017年04月01日
 * @reviewer
 * @description
 * @see
 */
public class MainMenuVo {
    private Long id;
    private Integer code;
    private String name;
    private String description;
    private Long [] menuitem_id;//此id是MenuItem类里的menuitem_id;

    public MainMenuVo() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long[] getMenuitem_id() {
        return menuitem_id;
    }

    public void setMenuitem_id(Long[] menuitem_id) {
        this.menuitem_id = menuitem_id;
    }
}