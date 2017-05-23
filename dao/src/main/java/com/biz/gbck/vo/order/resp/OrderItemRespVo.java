package com.biz.gbck.vo.order.resp;

import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.enums.order.ItemType;
import com.biz.gbck.enums.product.ProductShowStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 订单列表明细Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderItemRespVo implements IProduct, Comparable<OrderItemRespVo>, Serializable {

    private static final long serialVersionUID = -7211038111581086451L;

    //Id
    @JsonIgnore
    private Long id;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品logo
     */
    private String logo;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 单价
     */
    private Integer price;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品类型
     */
    private ItemType itemType = ItemType.NORMAL;

    /**
     * 是否已经申请退货
     */
    private Boolean returnFlag;

    /**
     * 状态
     */
    @JsonIgnore
    private Integer status;

    /**
     * 库存
     */
    @JsonIgnore
    private Integer stock;

    /**
     * 分类
     */
    private Long categoryId;


    public OrderItemRespVo() {
    }

    public OrderItemRespVo(OrderItem orderItem) {
        this();
        this.setId(orderItem.getId());
        this.setProductId(orderItem.getProductId());
        this.setProductCode(orderItem.getProductCode());
        this.setName(orderItem.getName());
        this.setLogo(orderItem.getLogo());
        this.setPrice(orderItem.getPrice());
        this.setQuantity(orderItem.getQuantity());
        this.setCategoryId(orderItem.getCategoryId());
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public int compareTo(OrderItemRespVo o) {
        return this.productCode != null && o.productCode != null ? this.productCode.compareTo(o.productCode) : this
                .productCode != null ? 1 : -1;
    }

    public boolean canBuy() {
        return this.status != null && this.status == ProductShowStatus.NORMAL.getValue();
    }


}
