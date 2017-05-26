package com.biz.gbck.vo.cart;

import com.biz.gbck.enums.order.ItemType;
import com.biz.gbck.vo.product.gbck.response.PurchaseProductItemVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codelogger.utils.BeanUtils;
import org.codelogger.utils.ValueUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 购物车明细vo
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemRespVo implements Comparable<ShopCartItemRespVo>, Serializable {

    private static final long serialVersionUID = 297542739517280818L;

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
    private String productId;

    /**
     * 分类Id
     */
    private Long categoryId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品图片
     */
    private String logo;

    /**
     * 箱规
     */
    private String standard;

    /**
     * 促销标签
     */
    private List<String> supportPromotions;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 售价
     */
    private Integer salePrice;

    /**
     * 本商品数量
     */
    private Integer quantity;

    /**
     * 最小起售数量
     */
    private Integer minQuantity;

    /**
     * 最大销售数量
     */
    private Integer maxQuantity;

    /**
     * 状态
     */
    private Integer status;

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
     * 商品类型
     */
    @JsonIgnore
    private ItemType itemType = ItemType.NORMAL;
    /**
     * 是否选中
     */
    private boolean selected = false;

    public ShopCartItemRespVo() {
    }

    public ShopCartItemRespVo(PurchaseProductItemVO itemVo) {
        this();
        this.setProductId(itemVo.getId());
        this.setProductCode(itemVo.getProductCode());
        this.setName(itemVo.getName());
        this.setLogo(itemVo.getLogo());
        this.setCreateTime(null);
        this.setMarketPrice(null);
        this.setSalePrice(itemVo.getSalePrice());
        if (itemVo.getSupportSpecialOffer() && ValueUtils.getValue(itemVo.getSpecialOfferPrice()) > 0) {
            this.setSalePrice(itemVo.getSpecialOfferPrice());
        }
        this.setMaxQuantity(itemVo.getMaxQuantity());
        this.setMinQuantity(itemVo.getMinQuantity());
        this.setStock(itemVo.getStock());
        this.setStatus(itemVo.getShowStatus().getValue());
        this.setStandard(null);
        this.setSubTitle(null);
        this.setCategoryId(itemVo.getCategoryId());
    }


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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
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

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getSupportPromotions() {
        return supportPromotions;
    }

    public void setSupportPromotions(List<String> supportPromotions) {
        this.supportPromotions = supportPromotions;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
