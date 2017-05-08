package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by xys on 2017/5/8.
 */
public class BrandSearchVo {

    /**
     * 品牌ID
     */
    private String id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    /**
     * {@linkplain BrandSearchVo#id}
     */
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
