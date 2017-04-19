package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

import static com.biz.gbck.common.Constant.DEFAULT_USER_LEVEL;

/**
 * 商品价格请求Vo
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class ProductPriceReqVo implements Serializable {
    private static final long serialVersionUID = 2057257398923902925L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    /**
     * 用户等级
     */
    private Integer userLevel = DEFAULT_USER_LEVEL;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "ProductPriceReqVo{" +
                "productCode='" + productCode + '\'' +
                ", depotCode='" + depotCode + '\'' +
                ", warehouseDepotCode='" + warehouseDepotCode + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}
