package com.biz.gbck.vo.order;

import java.io.Serializable;

/**
 * 订单列表请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderItemRespVo implements Comparable<OrderItemRespVo>, Serializable {

    private static final long serialVersionUID = -7211038111581086451L;

    //Id
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
     * 是否已经申请退货
     */
    private Boolean returnFlag = false;

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

    public Boolean getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(Boolean returnFlag) {
        this.returnFlag = returnFlag;
    }

    @Override
    public int compareTo(OrderItemRespVo o) {
        return this.productCode != null && o.productCode != null ? this.productCode.compareTo(o.productCode) : this
                .productCode != null ? 1 : -1;
    }


}
