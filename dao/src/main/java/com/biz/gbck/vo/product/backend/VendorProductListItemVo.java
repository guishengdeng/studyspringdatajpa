package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商家商品列表项 Vo
 *
 * @author david-liu
 * @date 2016年12月23日
 * @reviewer
 * @see
 */
public class VendorProductListItemVo implements Serializable {

    private static final long serialVersionUID = 5123685707461001263L;

    /**
     * 商品 ID
     */
    private String id;

    /**
     * 审核商品ID
     */
    private String productAuditId;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 市场价
     */
    private String marketPrice;

    /**
     * 销售价
     */
    private String finalPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 上下架状态
     */
    private String saleStatus;

    /**
     * 最后更新时间
     */
    private Timestamp lastUpdateTime;

    /**
     * 是否是编辑审核中
     */
    private Boolean inAudit;

    /**
     * 是否被锁定(已被锁定的商品不能被修改, 可以上下架)
     */
    private Boolean locked;

    /**
     * 商品 Logo
     */
    private String logo;


    /**
     * 商品审核状态
     *
     * @see com.biz.soa.enums.ProductAuditStatus
     */
    private String auditStatus;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getInAudit() {
        return inAudit;
    }

    public void setInAudit(Boolean inAudit) {
        this.inAudit = inAudit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getProductAuditId() {
        return productAuditId;
    }

    public void setProductAuditId(String productAuditId) {
        this.productAuditId = productAuditId;
    }
}
