package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.GeoLevelEnum;
import com.biz.gbck.vo.IRequestVo;
import java.util.List;

/**
 * 商品上架Vo
 *
 * @author 江南
 * @date 2017/1/18
 * @reviewer
 */
public class PutAwayVo implements IRequestVo {

    private static final long serialVersionUID = 8072771554627874085L;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品编码
     */
    private String productCode;

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
    private String quantity;

    /**
     * 最低限价
     */
    private Integer minPrice;

    /**
     * 商品可销售城市ID集合
     */
    private List<String> ids;

    /**
     * 区域等级
     */
    private GeoLevelEnum geoLevel;

    /**
     * vendorId 商家ID
     */
    private Long vendorId;

    public PutAwayVo() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public GeoLevelEnum getGeoLevel() {
        return geoLevel;
    }

    public void setGeoLevel(GeoLevelEnum geoLevel) {
        this.geoLevel = geoLevel;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

}
