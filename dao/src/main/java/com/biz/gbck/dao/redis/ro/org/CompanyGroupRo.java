package com.biz.gbck.dao.redis.ro.org;

import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * @author: liubin
 * @date 4/25/17 14:47
 */
@Ro(key = "org:CompanyGroupRo")
public class CompanyGroupRo extends BaseRedisObject<Long> {


    /**
     * 客户组编码
     */
    private String code;

    /**
     * 客户组名称
     */
    private String name;


    private Long parentId;


    private Integer childrenLevel;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getChildrenLevel() {
        return childrenLevel;
    }

    public void setChildrenLevel(Integer childrenLevel) {
        this.childrenLevel = childrenLevel;
    }
}
