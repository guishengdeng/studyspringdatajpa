package com.biz.gbck.vo.search;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品搜索结果单位 Vo
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 * @see
 */
public class ProductSearchResultEntityVo implements Serializable {
    private static final long serialVersionUID = 8463436890067136175L;

    /**
     * 商品 Id
     */
    private Long productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商家类型
     */
    private Integer type;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
