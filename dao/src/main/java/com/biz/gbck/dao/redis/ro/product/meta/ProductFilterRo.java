package com.biz.gbck.dao.redis.ro.product.meta;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 商品分类过滤条件
 *
 * @author david-liu
 * @date 2016年12月29日
 * @reviewer
 * @see
 */
@Ro(key = "product:ProductFilterRo")
public class ProductFilterRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 9111428376589146081L;

    /**
     * 分类 ID
     */
    @FieldSortedSet(key = "categoryId", score = "idx")
    private Long categoryId;

    /**
     * 顺序
     */
    private Integer idx;

    /**
     * 显示值
     */
    private String label;

    /**
     * 过滤字段
     */
    private String field;

    /**
     * 是否使用首字母
     */
    private Boolean usePrefix;

    /**
     * 是否显示更多字样
     */
    private Boolean hasMore;

    /**
     * 是否显示图片
     */
    private Boolean showImage;

    /**
     * 启用状态
     */
    private CommonStatusEnum status;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
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
