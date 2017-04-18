package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @date 2017/1/13
 * @reviewer
 */
public class IdNameVo implements Serializable {

    private static final long serialVersionUID = 2153400088297763232L;

    private String id;

    private String name;

    public IdNameVo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IdNameVo [id=" + id + ", name=" + name + "]";
    }

}
