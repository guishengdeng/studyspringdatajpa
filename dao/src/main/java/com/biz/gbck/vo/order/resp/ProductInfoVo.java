package com.biz.gbck.vo.order.resp;

import com.biz.gbck.enums.order.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 商品接口Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class ProductInfoVo implements IProduct {

    //Id
    @JsonIgnore
    protected Long id;

    /**
     * 商品Id
     */
    protected Long productId;

    /**
     * 商品编码
     */
    protected String productCode;

    /**
     * 商品logo
     */
    protected String logo;

    /**
     * 商品名称
     */
    protected String name;

    /**
     * 单价
     */
    protected Integer salePrice;

    /**
     * 市场价
     */
    protected Integer marketPrice;

    /**
     * 数量
     */
    protected Integer quantity;

    /**
     * 商品类型
     */
    protected ItemType itemType = ItemType.NORMAL;

    /**
     * 是否已经申请退货
     */
    protected Boolean returnFlag;

    /**
     * 状态
     */
    @JsonIgnore
    protected Integer status;

    /**
     * 库存
     */
    @JsonIgnore
    protected Integer stock;

    /**
     * 分类
     */
    protected Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Boolean getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(Boolean returnFlag) {
        this.returnFlag = returnFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
