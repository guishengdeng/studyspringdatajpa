package com.biz.gbck.dao.redis.ro.product;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 分类 Ro
 *
 * @author david-liu
 * @date 2016年12月29日
 * @reviewer
 * @see
 */
@Ro(key = "product:CategoryRo")
public class CategoryRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 982364366417909816L;

    /**
     * 名称
     */
    private String name;

    /**
     * 序号
     */
    private Integer idx;

    /**
     * Logo 图
     */
    private String logo;

    /**
     * 启用状态
     */
    private CommonStatusEnum status;

    /**
     * 父分类 ID
     */
    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
