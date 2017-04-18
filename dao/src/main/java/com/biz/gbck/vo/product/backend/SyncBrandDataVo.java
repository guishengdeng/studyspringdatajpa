package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 同步品牌数据Vo
 *
 * @author zhangcheng
 * @date 2017/2/10
 * @reviewer
 * @see
 */
public class SyncBrandDataVo implements Serializable {

    private static final long serialVersionUID = 2734502322832470988L;

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
     * 品牌状态
     */
    private CommonStatusEnum status;

    /**
     * 品牌顺序
     */
    private Integer idx;

    /**
     * 分类名称（不要分类id，需要分类中文名称）
     */
    private String categoryName;

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

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "SyncBrandDataVo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", EnglishName='" + EnglishName + '\'' +
                ", status=" + status +
                ", idx=" + idx +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
