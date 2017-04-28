package com.biz.gbck.dao.redis.ro.org;

import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    private String childrenIds;


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

    public String getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(String childrenIds) {
        this.childrenIds = childrenIds;
    }

    public List<Long> getChildrendIdList(String childrenIds) {

        List<Long> childrenIdList = new ArrayList<>();
        if (StringUtils.isNotBlank(childrenIds)) {


             childrenIdList = Arrays.stream(childrenIds.split(",")).map(childId -> Long.valueOf(childId)).collect(Collectors.toList());
        }
        return childrenIdList;
    }

    public void setChildrenIdStr(List<Long> ids) {

    }
}
