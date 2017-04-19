package com.biz.gbck.dao.redis.ro.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 品牌 Ro
 *
 * @author david-liu
 * @date 2016年12月29日
 * @reviewer
 * @see
 */
@Ro(key = "product:BrandRo")
public class BrandRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 1191428805523200959L;

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
     * 品牌描述
     */
    private String description;

    /**
     * 序号
     */
    private Integer idx;

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
     * SEO 描述
     */
    private String seoDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
