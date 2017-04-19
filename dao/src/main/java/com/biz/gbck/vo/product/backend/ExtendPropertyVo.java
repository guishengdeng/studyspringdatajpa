package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

/**
 * 分类属性 Vo
 *
 * @author yumin.wang
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class ExtendPropertyVo implements Serializable {

    private static final long serialVersionUID = -1294737535416958012L;

    /**
     * 属性id
     */
    private String id;

    /**
     * 属性值
     */
    private String value;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 显示顺序
     */
    private Integer idx;

    /**
     * 值所属的属性ID
     */
    private Long productExtendId;

    /**
     * 是否选中
     */
    private Boolean selected;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Long getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(Long productExtendId) {
        this.productExtendId = productExtendId;
    }

}
