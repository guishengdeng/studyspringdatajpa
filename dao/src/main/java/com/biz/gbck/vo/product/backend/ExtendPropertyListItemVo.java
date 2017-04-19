package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;

public class ExtendPropertyListItemVo implements Serializable {

    private static final long serialVersionUID = 3432012378375118468L;

    /**
     * ID
     */
    private String id;

    /**
     * 属性值
     */
    private String value;

    /**
     * 值的排序
     */
    private Integer idx;

    /**
     * 状态 启用or不启用
     */
    private CommonStatusEnum status;

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
