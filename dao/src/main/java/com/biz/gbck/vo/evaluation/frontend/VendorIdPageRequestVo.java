package com.biz.gbck.vo.evaluation.frontend;

import com.biz.gbck.vo.IRequestVo;
import com.biz.gbck.vo.product.backend.SearchPageVo;

/**
 * @author yangzichun
 * @date 2017/2/15
 */
public class VendorIdPageRequestVo extends SearchPageVo implements IRequestVo {
    private static final long serialVersionUID = -5830718433719833893L;
    // 商铺 id
    private Long vendorId;
    // 商品 id
    private Long productId;
    // 是否有图
    private boolean imageEvaluation;
    // 商品编码
    private String productCode;
    // 商品类型，A类商品、B类商品
    private String productType;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

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

    public boolean isImageEvaluation() {
        return imageEvaluation;
    }

    public void setImageEvaluation(boolean imageEvaluation) {
        this.imageEvaluation = imageEvaluation;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
