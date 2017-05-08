package com.biz.gbck.vo.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codelogger.utils.BeanUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 购物车明细vo
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemRespVo implements Comparable<ShopCartItemRespVo>, Serializable {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品子标题
     */
    private String subTitle;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品图片
     */
    private String logo;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 优惠标签
     */
    private List<String> coupons;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 售价
     */
    private Integer price;

    /**
     * 本商品数量
     */
    private Integer quantity;

    /**
     * 最小起售数量
     */
    private Integer minQuantity;

    /**
     * 库存数量
     */
    private Integer stock = 0;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Timestamp createTime;

    /**
     * 最后更新时间
     */
    @JsonIgnore
    private Timestamp updateTime;

    /**
     * 是否选中
     */
    private boolean selected = false;

    /**
     * 是否可买
     */
    private boolean canBuy = true;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isCanBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<String> coupons) {
        this.coupons = coupons;
    }

    @Override
    public int compareTo(ShopCartItemRespVo o) {
        if (this.getCreateTime() == null || o == null || o.getCreateTime() == null) {
            return 1;
        }
        return (int) (o.getCreateTime().getTime() - this.getCreateTime().getTime());
    }

    public ShopCartItemRespVo copy(){
        return BeanUtils.clone(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
