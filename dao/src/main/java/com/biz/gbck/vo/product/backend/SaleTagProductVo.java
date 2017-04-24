package com.biz.gbck.vo.product.backend;

import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/11
 */
public class SaleTagProductVo implements Serializable {

    private static final long serialVersionUID = 7464857998568998178L;

    private Long saletagId;

    private Long productId;

    private String productName;

    private Double idx;

    public SaleTagProductVo(Long saletagId, Long productId, String productName, Double idx) {
        this.saletagId = saletagId;
        this.productId = productId;
        this.productName = productName;
        this.idx = idx;
    }

    public SaleTagProductVo() {
    }

    public Long getSaletagId() {
        return saletagId;
    }

    public void setSaletagId(Long saletagId) {
        this.saletagId = saletagId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getIdx() {
        return idx;
    }

    public void setIdx(Double idx) {
        this.idx = idx;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
