package com.biz.gbck.vo.product;

import com.biz.gbck.enums.product.VendorTypeEnum;
import java.io.Serializable;

/**
 * @author david-liu
 * @date 2017年01月13日
 * @reviewer
 * @see
 */
public class ProductTypeWithIndexVo implements Serializable {
    private static final long serialVersionUID = 5576446987566456382L;

    /**
     * 序号
     */
    private Integer idx;

    /**
     * 商品类型
     */
    private VendorTypeEnum vendorType;

    public ProductTypeWithIndexVo(Integer idx, VendorTypeEnum vendorType) {
        this.idx = idx;
        this.vendorType = vendorType;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
    }

    @Override
    public String toString() {
        return "ProductTypeWithIndexVo{" +
                "idx=" + idx +
                ", vendorType=" + vendorType +
                '}';
    }
}
