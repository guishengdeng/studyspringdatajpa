package com.biz.gbck.dao.redis.ro.product;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;

/**
 * 商品分类过滤条件属性值 Ro
 *
 * @author david-liu
 * @date 2016年12月30日
 * @reviewer
 * @see
 */
@Ro(key = "product:ProductFilterItemRo")
public class ProductFilterItemRo extends BaseRedisObject<Long> implements Serializable {

    private static final long serialVersionUID = 3044033587459072764L;
    /**
     * 过滤条件 ID
     */
    @FieldSortedSet(key = "filterId", score = "idx")
    private Long filterId;

    /**
     * 首字母
     */
    private String prefix;

    /**
     * 显示值
     */
    private String label;

    /**
     * 过滤值
     */
    private String value;

    /**
     * 图片信息
     */
    private String image;

    /**
     * 顺序
     */
    private Integer idx;

    /**
     * 是否高亮显示
     */
    private Boolean highlightShow;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    public Long getFilterId() {
        return filterId;
    }

    public void setFilterId(Long filterId) {
        this.filterId = filterId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Boolean getHighlightShow() {
        return highlightShow;
    }

    public void setHighlightShow(Boolean hightlightShow) {
        this.highlightShow = hightlightShow;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
