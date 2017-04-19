package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 添加商品搜索过滤条件VO
 *
 * @author wangyumin
 * @date 2017年1月4日
 */
public class CreateProductFilterVo implements Serializable {

    private static final long serialVersionUID = 43057902300021897L;

    /**
     * ID
     */
    private Long id;

    /**
     * 所属分类ID
     */
    private Long categoryId;

    /**
     * 显示名称
     */
    private String label;

    /**
     * 过滤的字段
     */
    private String field;

    /**
     * 是否展示首字母
     */
    private Boolean usePrefix;

    /**
     * 展示更多
     */
    private Boolean hasMore;

    /**
     * 展示图片
     */
    private Boolean showImage;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Boolean getUsePrefix() {
        return usePrefix;
    }

    public void setUsePrefix(Boolean usePrefix) {
        this.usePrefix = usePrefix;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Boolean getShowImage() {
        return showImage;
    }

    public void setShowImage(Boolean showImage) {
        this.showImage = showImage;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}

