package com.biz.gbck.vo.order.req;

import com.biz.gbck.vo.IRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 订单商品明细Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class ProductItemReqVo implements IRequestVo {

    private static final long serialVersionUID = -5741991657192871333L;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
