package com.biz.gbck.vo;

import java.io.Serializable;

/**
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class PageVo implements Serializable {
    private static final long serialVersionUID = -5113775306566869929L;

    /**
     * 起始页
     */
    private Integer startPage;

    /**
     * 结束页
     */
    private Integer endPage;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 总共记录条数
     */
    private Integer totalElementsCount;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 其实元素下标
     */
    private Integer startElementIndex;

    /**
     * 终止元素下标
     */
    private Integer endElementIndex;

    public PageVo(Integer startPage, Integer endPage, Integer currentPage, Integer totalElementsCount, Integer pageSize, Integer startElementIndex, Integer endElementIndex) {
        this.startPage = startPage;
        this.endPage = endPage;
        this.currentPage = currentPage;
        this.totalElementsCount = totalElementsCount;
        this.pageSize = pageSize;
        this.startElementIndex = startElementIndex;
        this.endElementIndex = endElementIndex;
    }

    public Integer getStartElementIndex() {
        return startElementIndex;
    }

    public void setStartElementIndex(Integer startElementIndex) {
        this.startElementIndex = startElementIndex;
    }

    public Integer getEndElementIndex() {
        return endElementIndex;
    }

    public void setEndElementIndex(Integer endElementIndex) {
        this.endElementIndex = endElementIndex;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalElementsCount() {
        return totalElementsCount;
    }

    public void setTotalElementsCount(Integer totalElementsCount) {
        this.totalElementsCount = totalElementsCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "startPage=" + startPage +
                ", endPage=" + endPage +
                ", currentPage=" + currentPage +
                ", totalElementsCount=" + totalElementsCount +
                ", pageSize=" + pageSize +
                ", startElementIndex=" + startElementIndex +
                ", endElementIndex=" + endElementIndex +
                '}';
    }
}
