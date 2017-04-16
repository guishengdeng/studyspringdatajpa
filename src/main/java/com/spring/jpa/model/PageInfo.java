package com.spring.jpa.model;

/**
 * PageInfo
 *
 * @author guisheng.deng
 * @date 2017年04月06日
 * @reviewer
 * @description
 * @see
 */
public class PageInfo {
    private Integer pageNum;
    private Integer pageLimit;
    private String sortName;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public PageInfo() {
    }
}