package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 同步商品销售标签Vo
 *
 * @author zhangcheng
 * @date 2017/2/10
 * @reviewer
 * @see
 */
public class SyncSaleTagDataVo implements Serializable {

    private static final long serialVersionUID = 5202222702814481527L;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签logo
     */
    private String logo;

    /**
     * 显示顺序
     */
    private Integer idx;

    /**
     * 富文本
     */
    private String rawHtml;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 分类名称（不要分类id，需要分类的中文名称）
     */
    private String categoryName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
