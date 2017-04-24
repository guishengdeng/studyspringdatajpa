package com.biz.gbck.vo.search;

import java.io.Serializable;
import java.util.List;

/**
 * 商品索引返回Vo
 *
 * @author david-liu
 * @date 2017年01月20日
 * @reviewer
 */
public class ProductIdxRespVo implements Serializable {
    private static final long serialVersionUID = 4988632514261064464L;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 商品索引
     */
    private List<ProductIdxVo> indices;

    /**
     * 门店编码(如果是构建B类商品索引, 该值不为空)
     */
    private String depotCode;

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public List<ProductIdxVo> getIndices() {
        return indices;
    }

    public void setIndices(List<ProductIdxVo> indices) {
        this.indices = indices;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }
}
