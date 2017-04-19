package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * 商品库存Vo
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class ProductStockVo implements Serializable {
    private static final long serialVersionUID = 7068587976094389326L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 全国库存(A类商品使用)
     */
    private Integer countryStock;

    /**
     * 门店编码(B类商品使用, 如果没开启快喝模式不会返回)
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    /**
     * 门店库存(B类商品使用)
     */
    private Integer depotStock;

    /**
     * B类商品全省库存(B类商品使用)
     */
    private Integer provinceStock;

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

    public Integer getCountryStock() {
        return countryStock;
    }

    public void setCountryStock(Integer countryStock) {
        this.countryStock = countryStock;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public Integer getDepotStock() {
        return depotStock;
    }

    public void setDepotStock(Integer depotStock) {
        this.depotStock = depotStock;
    }

    public Integer getProvinceStock() {
        return provinceStock;
    }

    public void setProvinceStock(Integer provinceStock) {
        this.provinceStock = provinceStock;
    }

    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    @Override
    public String toString() {
        return "ProductStockVo{" +
                "productCode='" + productCode + '\'' +
                ", productType=" + productType +
                ", countryStock=" + countryStock +
                ", depotCode='" + depotCode + '\'' +
                ", warehouseDepotCode='" + warehouseDepotCode + '\'' +
                ", depotStock=" + depotStock +
                ", provinceStock=" + provinceStock +
                '}';
    }
}
