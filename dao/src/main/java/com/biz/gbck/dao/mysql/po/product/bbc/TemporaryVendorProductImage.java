package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.support.jpa.converter.ListStringConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 该破用于存储A类商品的图片新的名称，临时用，以后可废弃
 *
 * @author zhangcheng
 * @date 2017/2/22
 * @reviewer
 * @see
 */
@Entity
@Table(name = "pro_temporary_vendor_product_images")
public class TemporaryVendorProductImage extends BaseEntity {

    /**
     * A类商品编码
     */
    @Column(nullable = false)
    private String productCode;

    /**
     * A类商品介绍图片
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
    private List<String> introImages;

    /**
     * A类商品logo图片
     */
    @Column
    private String logo;

    /**
     * A类商品商品图片（商品轮播图）
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListStringConverter.class)
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
