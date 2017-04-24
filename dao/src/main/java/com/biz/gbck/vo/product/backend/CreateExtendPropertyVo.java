package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

public class CreateExtendPropertyVo implements IExtendPropertyVo {

    private static final long serialVersionUID = 179487854706756908L;

    /**
     * ID
     */
    private Long id;

    /**
     * 属性值
     */
    private String value;

    /**
     * 状态 启用or不启用
     */
    private CommonStatusEnum status;

    /**
     * 值所属的属性ID
     */
    private Long productExtendId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Long getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(Long productExtendId) {
        this.productExtendId = productExtendId;
    }

}
