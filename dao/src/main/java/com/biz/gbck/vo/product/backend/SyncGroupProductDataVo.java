package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 导入组合商品数据Vo
 *
 * @author zhangcheng
 * @date 2017/3/10
 * @reviewer
 * @see
 */
public class SyncGroupProductDataVo implements Serializable {

    private static final long serialVersionUID = -7605432519207655115L;

    /**
     * 组合商品包装号
     */
    private String mixProductCode;

    /**
     * 组合商品描述
     */
    private String mixProductDescription;

    /**
     * 组合商品定价百分比
     */
    private String mixProductPercentage;

    /**
     * 组合商品明细
     */
    private List<SyncGroupProductItemVo> itemVos;

    /**
     * 组合商品的价格
     */
    private String mixPrice;

    /**
     * 组合商品Logo
     */
    private String logo;

    /**
     * 组合商品图片（商品轮播图）
     */
    private List<String> productImages;

    /**
     * 组合介绍图片
     */
    private List<String> introImages;

    /**
     * 组合商品的分类名称
     */
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public String getMixPrice() {
        return mixPrice;
    }

    public void setMixPrice(String mixPrice) {
        this.mixPrice = mixPrice;
    }

    public String getMixProductCode() {
        return mixProductCode;
    }

    public void setMixProductCode(String mixProductCode) {
        this.mixProductCode = mixProductCode;
    }

    public String getMixProductDescription() {
        return mixProductDescription;
    }

    public void setMixProductDescription(String mixProductDescription) {
        this.mixProductDescription = mixProductDescription;
    }

    public String getMixProductPercentage() {
        return mixProductPercentage;
    }

    public void setMixProductPercentage(String mixProductPercentage) {
        this.mixProductPercentage = mixProductPercentage;
    }

    public List<SyncGroupProductItemVo> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<SyncGroupProductItemVo> itemVos) {
        this.itemVos = itemVos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
