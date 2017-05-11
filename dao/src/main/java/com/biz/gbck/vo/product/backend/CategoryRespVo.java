package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.enums.CommonStatusEnum;

import java.io.Serializable;

/**
 * @author yangzichun
 * @date 2017/4/25
 */
public class CategoryRespVo implements Serializable {
    private static final long serialVersionUID = -3702674180331583248L;
    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类排序
     */
    private Integer idx;

    /**
     * 分类图标
     */
    private String logo;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 父分类
     */
    private Category parent;

    /**
     * SEO 标题
     */
    private String seoTitle;

    /**
     * SEO 关键字
     */
    private String seoKeywords;

    /**
     * SEO 描述
     */
    private String seoDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }
}
