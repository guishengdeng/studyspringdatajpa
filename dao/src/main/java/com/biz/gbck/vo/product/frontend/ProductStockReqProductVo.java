package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.vo.product.RapidProductItemVo;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * B类商品库存请求Vo
 *
 * @author david-liu
 * @date 2017年03月10日
 * @reviewer
 */
public class ProductStockReqProductVo implements Serializable {
    private static final long serialVersionUID = -3532065619932888601L;

    private String productCode;

    private Boolean isRapidProduct = Boolean.FALSE;

    private List<RapidProductItemVo> rapidProductItems = Lists.newArrayList();

    public ProductStockReqProductVo(String productCode, Boolean isRapidProduct, List<RapidProductItemVo> rapidProductItems) {
        this.productCode = productCode;
        this.isRapidProduct = isRapidProduct;
        this.rapidProductItems = rapidProductItems;
    }

    public ProductStockReqProductVo() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Boolean getRapidProduct() {
        return isRapidProduct;
    }

    public void setRapidProduct(Boolean rapidProduct) {
        isRapidProduct = rapidProduct;
    }

    public List<RapidProductItemVo> getRapidProductItems() {
        return rapidProductItems;
    }

    public void setRapidProductItems(List<RapidProductItemVo> rapidProductItems) {
        this.rapidProductItems = rapidProductItems;
    }

    @Override
    public String toString() {
        return "ProductStockReqProductVo{" + "productCode='" + productCode + '\'' + ", isRapidProduct=" +
                isRapidProduct + ", rapidProductItems=" + rapidProductItems + '}';
    }
}
