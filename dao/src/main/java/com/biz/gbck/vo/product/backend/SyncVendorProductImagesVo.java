package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;

/**
 * 同步A类商品图片Vo
 *
 * @author zhancheng
 * @date 2017/2/22
 * @reivewer
 * @see
 */
public class SyncVendorProductImagesVo implements Serializable {

    private static final long serialVersionUID = 7166730495927397559L;

    /**
     * A类商品编码
     */
    private String productCode;

    /**
     * A类商品介绍图片
     */
    private List<String> introImages;

    /**
     * A类商品logo图片
     */
    private String logo;

    /**
     * A类商品商品图片（商品轮播图）
     */
    private List<String> productImages;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }
}
