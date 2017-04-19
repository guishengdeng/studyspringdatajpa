package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * 商品价格Vo
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class ProductPriceVo implements Serializable {
    private static final long serialVersionUID = 593061694853183842L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 市场价
     * <pre>
     *     1. 如果商品是A类商品, 返回A类商品全国价格的市场价格
     *     2. 如果商品是B类商品, 返回B类商品门店价格的市场价格
     * </pre>
     */
    private Integer marketPrice;

    /**
     * 销售价
     * <pre>
     *     1. 如果商品是A类商品, 返回A类商品全国价格的市场价格
     *     2. 如果商品是B类商品, 返回B类商品门店价格的市场价格
     * </pre>
     */
    private Integer finalPrice;

    /**
     * 省仓门店市场价
     * <pre>
     *     1. 如果商品是A类商品, 返回空
     *     2. 如果商品是B类商品
     *        > 如果已经开启快喝模式, 返回基于当前定位就近门店对应的省仓门店的市场价
     *        > 如果未开启快喝模式, 返回当成城市对应的省仓门店的市场价
     * </pre>
     */
    private Integer warehouseDepotMarketPrice;

    /**
     * 省仓门店结算价
     * <pre>
     *     1. 如果商品是A类商品, 返回空
     *     2. 如果商品是B类商品
     *        > 如果已经开启快喝模式, 返回基于当前定位就近门店对应的省仓门店的结算价
     *        > 如果未开启快喝模式, 返回当成城市对应的省仓门店的结算价
     * </pre>
     */
    private Integer warehouseDepotFinalPrice;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
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

    public Integer getWarehouseDepotMarketPrice() {
        return warehouseDepotMarketPrice;
    }

    public void setWarehouseDepotMarketPrice(Integer warehouseDepotMarketPrice) {
        this.warehouseDepotMarketPrice = warehouseDepotMarketPrice;
    }

    public Integer getWarehouseDepotFinalPrice() {
        return warehouseDepotFinalPrice;
    }

    public void setWarehouseDepotFinalPrice(Integer warehouseDepotFinalPrice) {
        this.warehouseDepotFinalPrice = warehouseDepotFinalPrice;
    }

    @Override
    public String toString() {
        return "ProductPriceVo{" +
                "productCode='" + productCode + '\'' +
                ", productType=" + productType +
                ", marketPrice=" + marketPrice +
                ", finalPrice=" + finalPrice +
                ", warehouseDepotMarketPrice=" + warehouseDepotMarketPrice +
                ", warehouseDepotFinalPrice=" + warehouseDepotFinalPrice +
                '}';
    }
}
