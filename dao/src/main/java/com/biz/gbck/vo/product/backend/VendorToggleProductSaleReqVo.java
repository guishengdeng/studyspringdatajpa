package com.biz.gbck.vo.product.backend;

import com.biz.gbck.vo.IRequestVo;
import java.util.List;

/**
 * 商家更改商品上下架状态请求 Vo
 *
 * @author david-liu
 * @date 2016年12月27日
 * @reviewer
 * @see
 */
public class VendorToggleProductSaleReqVo implements IRequestVo {

    private static final long serialVersionUID = 8050634180239792013L;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 销售标签 ID 集合
     */
    private List<Long> saleTagIds;

    /**
     * 商品角标 ID 集合
     */
    private List<Long> apartTagIds;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 销售价
     */
    private Integer finalPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 最低限价
     */
    private Integer limitPrice;

    /**
     * 区域等级
     */
    private Long geoLevel;

    /**
     * 区域 ID 集合
     */
    private List<Long> ids;

    /**
     * 是否应用到所有
     */
    private Boolean forAll;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getSaleTagIds() {
        return saleTagIds;
    }

    public void setSaleTagIds(List<Long> saleTagIds) {
        this.saleTagIds = saleTagIds;
    }

    public List<Long> getApartTagIds() {
        return apartTagIds;
    }

    public void setApartTagIds(List<Long> apartTagIds) {
        this.apartTagIds = apartTagIds;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Integer limitPrice) {
        this.limitPrice = limitPrice;
    }

    public Long getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(Long geoLevel) {
        this.geoLevel = geoLevel;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Boolean getForAll() {
        return forAll;
    }

    public void setForAll(Boolean forAll) {
        this.forAll = forAll;
    }
}
