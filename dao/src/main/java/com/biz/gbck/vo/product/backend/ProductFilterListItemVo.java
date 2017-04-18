package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 商品搜索条件列表项VO
 *
 * @author 王宇民
 * @date 2017年1月4日
 */
public class ProductFilterListItemVo implements Serializable {

    private static final long serialVersionUID = 6804360502307071313L;

    private String id;

    /**
     * 显示名称
     */
    private String label;

    /**
     * 过滤的字段
     */
    private String field;

    /**
     * 是否首字母搜索
     */
    private Boolean usePrefix;

    /**
     * 是否显示更多
     */
    private Boolean hasMore;

    /**
     * 是否显示图片
     */
    private Boolean showImage;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
