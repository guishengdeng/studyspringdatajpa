package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.enums.product.ProductShowStatus;
import com.biz.gbck.enums.product.VendorTypeEnum;
import java.io.Serializable;

/**
 * BBC 购物车商品列表项 Vo
 *
 * @author lei
 * @date 2017年01月16日
 * @reviewer
 * @see
 */
public class ShopCartProductResponseVo implements Serializable {

    private static final long serialVersionUID = 2132522968239735433L;

    /**
     * 商品类别(A类或B类)
     */
    private VendorTypeEnum vendorType;

    /**
     * 商品 ID(必选)
     */
    private Long productId;

    /**
     * 商品编码(必选)
     */
    private String productCode;

    /**
     * 商品名称(必选)
     */
    private String productName;

    /**
     * 商品子标题
     */
    private String subTitle;

    /**
     * 商品图标(必选)
     */
    private String logo;

    /**
     * 商家Id(A类商品必传)
     */
    private String vendorId;

    /**
     * 商品市场价(单位:分)
     */
    private Integer marketPrice;

    /**
     * 商品销售价(可选, 无价格或无效价格不传)
     */
    private Integer finalPrice;

    /**
     * 省仓门店商品市场价(单位:分)
     */
    private Integer warehouseDepotMarketPrice;

    /**
     * 省仓门店商品销售价(可选, 无价格或无效价格不传)
     */
    private Integer warehouseDepotFinalPrice;

    /**
     * 可用库存
     */
    private Integer quantity = 0;

    /**
     * 全省可用库存
     */
    private Integer warehouseQuantity = 0;

    /**
     * 商品展示状态(必选, 默认正常)
     */
    private ProductShowStatus status = ProductShowStatus.NORMAL;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductShowStatus getStatus() {
        return status;
    }

    public void setStatus(ProductShowStatus status) {
        this.status = status;
    }

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
    }

    public Integer getWarehouseDepotFinalPrice() {
        return warehouseDepotFinalPrice;
    }

    public void setWarehouseDepotFinalPrice(Integer warehouseDepotFinalPrice) {
        this.warehouseDepotFinalPrice = warehouseDepotFinalPrice;
    }

    public Integer getWarehouseDepotMarketPrice() {
        return warehouseDepotMarketPrice;
    }

    public void setWarehouseDepotMarketPrice(Integer warehouseDepotMarketPrice) {
        this.warehouseDepotMarketPrice = warehouseDepotMarketPrice;
    }

    public Integer getWarehouseQuantity() {
        return warehouseQuantity;
    }

    public void setWarehouseQuantity(Integer warehouseQuantity) {
        this.warehouseQuantity = warehouseQuantity;
    }

    @Override
    public String toString() {
        return "ShopCartProductResponseVo{" +
                "vendorType=" + vendorType +
                ", productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", logo='" + logo + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", marketPrice=" + marketPrice +
                ", finalPrice=" + finalPrice +
                ", warehouseDepotMarketPrice=" + warehouseDepotMarketPrice +
                ", warehouseDepotFinalPrice=" + warehouseDepotFinalPrice +
                ", quantity=" + quantity +
                ", warehouseQuantity=" + warehouseQuantity +
                ", status=" + status +
                '}';
    }
}
