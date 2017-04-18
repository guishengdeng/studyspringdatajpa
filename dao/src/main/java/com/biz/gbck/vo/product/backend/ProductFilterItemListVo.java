package com.biz.gbck.vo.product.backend;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 分类中列表VO
 *
 * @author wangyumin
 * @date 2017年1月5日
 */
public class ProductFilterItemListVo implements Serializable {

    private static final long serialVersionUID = -9199281773736516281L;

    /**
     * ID
     */
    private String id;

    /**
     * 首字母
     */
    private String prefix;

    /**
     * 值的显示名称
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 展示顺序
     */
    private Integer idx;

    /**
     * 是否特殊显示
     */
    private Boolean highlightShow;

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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
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

}
