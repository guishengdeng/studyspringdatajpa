package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 同步商家端品牌Vo
 *
 * @author zhangcheng
 * @date 2017/2/22
 * @reviewer
 * @see
 */
public class SyncVendorBrandDataVo implements Serializable {

    private static final long serialVersionUID = -738811145018948742L;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌英文名称（如果有就传，没有就不传）
     */
    private String EnglishName;

    /**
     * 品牌状态("1"为正常，"0"为删除)
     */
    private Integer status;

    /**
     * 品牌顺序
     */
    private Integer idx;

    /**
     * 分类名称（不要分类id，需要分类中文名称）
     */
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
