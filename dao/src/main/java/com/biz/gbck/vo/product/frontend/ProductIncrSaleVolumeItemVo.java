package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * 商品增加销量Vo(记录商品编码和数量之间的对应关系)
 *
 * Created by david-liu on 2017/04/21 12:08.
 */
public class ProductIncrSaleVolumeItemVo implements Serializable {
    private static final long serialVersionUID = 1175252613604111686L;

    /**
     * 商品编码
     */
    @NotNull(message = "商品编码不能为空")
    private String productCode;

    /**
     * 出售数量
     */
    @NotNull(message = "商品已售数量不能为空")
    private Integer soldQuantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
