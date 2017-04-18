package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * 初始化A类商品价格Vo
 *
 * @author david-liu
 * @date 2017年02月23日
 * @reviewer
 */
public class InitTypeAPriceVo implements Serializable {
    private static final long serialVersionUID = -9141224568237496017L;

    /**
     * 我们这边的A类productCode（也就是idService生成的那个productCode）
     */
    private String productCode;

    /**
     * 最低限价
     */
    private Integer limitPrice;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 最终售价
     */
    private Integer finalPrice;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Integer limitPrice) {
        this.limitPrice = limitPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }
}
