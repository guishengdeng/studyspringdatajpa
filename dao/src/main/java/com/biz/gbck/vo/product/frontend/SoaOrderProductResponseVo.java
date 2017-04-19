package com.biz.gbck.vo.product.frontend;

/**
 * @author yanweijin
 * @date 2017/1/23
 */
public class SoaOrderProductResponseVo extends ShopCartProductResponseVo {

    private static final long serialVersionUID = -4981104587512238679L;


    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 分类ID
     */
    private Long categoryId;

    //    private FrontendDepotType depotType;

    //重量,单位为克(g)
    private Integer weight;

    private String vendorName;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    //
    //    public FrontendDepotType getDepotType() {
    //        return depotType;
    //    }
    //
    //    public void setDepotType(FrontendDepotType depotType) {
    //        this.depotType = depotType;
    //    }
}
