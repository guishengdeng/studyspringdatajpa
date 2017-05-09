package com.biz.gbck.vo.price;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 商品价格ReqVo
 *
 * Created by david-liu on 2017/05/02 11:33.
 */
public class PriceGroupProductCodesPriceReqVO implements Serializable {
    private static final long serialVersionUID = 7310166382112876563L;

    /**
     * 价格组Id
     */
    @NotNull(message = "商品价格组ID不能为空")
    private Long priceGroupId;

    /**
     * 商品编码集合
     */
    @NotNull(message = "商品编码集合不能为空")
    @NotEmpty(message = "商品编码集合不能为空")
    private List<String> productCodes;

    public PriceGroupProductCodesPriceReqVO(Long priceGroupId, List<String> productCodes) {
        this.priceGroupId = priceGroupId;
        this.productCodes = productCodes;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }

    @Override
    public String toString() {
        return "ProductPriceReqVo{" +
                "priceGroupId=" + priceGroupId +
                ", productCodes=" + productCodes +
                '}';
    }
}
