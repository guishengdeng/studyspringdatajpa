package com.spring.jpa.model.vo;

import java.io.Serializable;

/**
 * PersonVoTest
 *
 * @author guisheng.deng
 * @date 2017年03月28日
 * @reviewer
 * @description
 * @see
 */
public class PersonVoTest implements Serializable{
    private Integer id;
    private String name;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PersonVoTest() {

    }

    public PersonVoTest(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}