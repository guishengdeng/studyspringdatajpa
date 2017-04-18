package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 修改商品搜索过滤条件VO
 *
 * @author 王宇民
 * @date 2017年1月4日
 */
public class UpdateProductFilterVo implements Serializable {

    private static final long serialVersionUID = 8981526994508787759L;

    /**
     * ID
     */
    private String id;

    /**
     * 显示的名称
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
     * 是否显示更多
     */
    private Boolean hasMore;

    /**
     * 是否显示图片
     */
    private Boolean showImage;

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

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

}
