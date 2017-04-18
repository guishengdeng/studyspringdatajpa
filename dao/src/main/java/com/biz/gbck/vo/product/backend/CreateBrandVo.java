package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;

/**
 * 分类创建 Vo(后台用)
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class CreateBrandVo implements IBrandVo {

    private static final long serialVersionUID = -2659682776828712202L;

    /**
     * ID
     */
    private Long id;

    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌名(英文)
     */
    private String nameEn;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌 Logo
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类 Id
     */
    private Long categoryId;

    /**
     * 状态
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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    @Override
    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    @Override
    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    @Override
    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    @Override
    public String toString() {
        return "CreateBrandVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", logo='" + logo + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", status=" + status +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
