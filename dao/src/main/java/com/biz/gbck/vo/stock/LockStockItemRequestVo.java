package com.biz.gbck.vo.stock;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品库存锁定请求vo
 *
 * @author lei
 */
public class LockStockItemRequestVo implements Serializable {
    private static final long serialVersionUID = -5743090390191730985L;

    private String productCode;

    /**
     * 数量(正数锁定，负数释放锁定)
     */
    private int quantity;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
