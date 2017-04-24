package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

/**
 * 后台创建分类 Vo
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class CreateCategoryVo implements ICategoryVo {

    private static final long serialVersionUID = 9190860765473360280L;

    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类的Idx
     */
    private Integer idx;

    /**
     * 分类商品的图标
     */
    private String logo;

    /**
     * 启用状态
     */
    private CommonStatusEnum status;

    /**
     * SEO 标题
     */
    private String seoTitle;

    /**
     * SEO 关键字
     */
    private String seoKeywords;

    /**
     * SEO 描述信息
     */
    private String seoDescription;

    /**
     * 父分类 ID
     */
    private Long parentCategoryId;

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

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateCategoryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idx=" + idx +
                ", logo='" + logo + '\'' +
                ", status=" + status +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                '}';
    }
}
