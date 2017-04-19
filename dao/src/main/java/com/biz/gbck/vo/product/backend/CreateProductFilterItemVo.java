package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 新建搜索条件值VO
 *
 * @author wangyumin
 * @date 2017年1月5日
 */
public class CreateProductFilterItemVo implements Serializable {

    private static final long serialVersionUID = 9091523277657306722L;

    /**
     * 商品首字母
     */
    private String prefix;

    /**
     * 显示的名称
     */
    private String label;

    /**
     * 条件项属性值
     */
    private String value;

    /**
     * 显示图片
     */
    private String image;

    /**
     * 是否特殊显示
     */
    private Boolean highlightShow;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 值所属的条件ID
     */
    private Long productFilterId;

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

    public Boolean getHighlightShow() {
        return highlightShow;
    }

    public void setHighlightShow(Boolean highlightShow) {
        this.highlightShow = highlightShow;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Long getProductFilterId() {
        return productFilterId;
    }

    public void setProductFilterId(Long productFilterId) {
        this.productFilterId = productFilterId;
    }

}
