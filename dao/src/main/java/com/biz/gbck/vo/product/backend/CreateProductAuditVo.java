package com.biz.gbck.vo.product.backend;


import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.IRequestVo;
import java.util.List;

/**
 * 商品创建 Vo
 *
 * @author david-liu
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
public class CreateProductAuditVo implements IProductAuditVo, IRequestVo {

    private static final long serialVersionUID = 6210672023800879916L;

    /**
     * ID
     */
    private Long id;

    /**
     * 商家 ID
     */
    private Long vendorId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品平台类型
     */
    private VendorTypeEnum vendorType = VendorTypeEnum.TYPE_A;

    /**
     * 商品副标题
     */
    private String subTitle;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品国际条码
     */
    private String i18nCode;

    /**
     * 商品简介
     */
    private String breif;

    /**
     * 商品品牌 ID
     */
    private Long brandId;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 商品介绍
     */
    private List<String> introImages;

    /**
     * 商品扩展属性(产地, 香型, 净含量等)
     */
    private List<ExtendVo> extendVos;

    /**
     * 商品主图
     */
    private String logo;

    /**
     * 商品展示图
     */
    private List<String> images;

    /**
     * SEO 标题
     */
    private String seoTitle;

    /**
     * SEO 关键字
     */
    private String seoKeywords;

    /**
     * SEO 描述
     */
    private String seoDescription;

    /**
     * 商品重量
     */
    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public VendorTypeEnum getType() {
        return this.vendorType;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    @Override
    public String getBreif() {
        return breif;
    }

    public void setBreif(String breif) {
        this.breif = breif;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public List<ExtendVo> getExtendVos() {
        return extendVos;
    }

    public void setExtendVos(List<ExtendVo> extendVos) {
        this.extendVos = extendVos;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    @Override
    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    @Override
    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    @Override
    public String toString() {
        return "CreateProductAuditVo{" +
                "id=" + id +
                ", vendorId=" + vendorId +
                ", name='" + name + '\'' +
                ", vendorType=" + vendorType +
                ", subTitle='" + subTitle + '\'' +
                ", productCode='" + productCode + '\'' +
                ", i18nCode='" + i18nCode + '\'' +
                ", breif='" + breif + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", introImages=" + introImages +
                ", extendVos=" + extendVos +
                ", logo='" + logo + '\'' +
                ", images=" + images +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", seoDescription='" + seoDescription + '\'' +
                ", weight=" + weight +
                '}';
    }
}
