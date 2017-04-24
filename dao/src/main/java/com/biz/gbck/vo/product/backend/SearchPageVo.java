package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * 分页搜索基础Vo
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/16
 */
public class SearchPageVo implements Serializable {

    private static final long serialVersionUID = -3715381632960044745L;

    /**
     * 默认页大小
     */
    private static final int DEFAULT_PAGE_SIZE = 20;

    private static final int DEFAULT_PAGE_NUMBER = 0;


    /**
     * 当前页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前第几页
     */
    private int pageIndex = DEFAULT_PAGE_NUMBER;

    /**
     * 搜索字段
     */
    private String searchKey;

    /**
     * 搜索值
     */
    private String searchValue;

    public SearchPageVo(int pageSize, int pageIndex, String searchKey, String searchValue) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.searchKey = searchKey;
        this.searchValue = searchValue;
    }

    public SearchPageVo() {
    }

    public String getSearchKey() {

        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "SearchPageVo{" +
                "pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", searchKey='" + searchKey + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
