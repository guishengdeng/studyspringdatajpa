package com.spring.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="mainmenu")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","menuItems"})

public class MainMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Integer code;
    @Column(length=50)
    private String name;
    @Column(length=255)
    private String description;
    @OneToMany(fetch=FetchType.LAZY,mappedBy="mainMenu")
    private List<MenuItem> menuItems;
    public MainMenu() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Integer getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

}
