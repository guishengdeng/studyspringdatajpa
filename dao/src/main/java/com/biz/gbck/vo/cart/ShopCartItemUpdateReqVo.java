package com.biz.gbck.vo.cart;

import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 修改购物车明细vo
 *
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemUpdateReqVo extends BaseRequestVo {

    private static final long serialVersionUID = -1730115632054116788L;

    /**
     * 商品编码
     */
    private String productId;

    /**
     * 数量
     */
    private int quantity = 1;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
