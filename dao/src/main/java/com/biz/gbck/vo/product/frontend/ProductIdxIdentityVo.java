package com.biz.gbck.vo.product.frontend;

import com.biz.gbck.enums.product.SaleStatusEnum;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 商品索引标识Vo
 *
 * @author david-liu
 * @date 2017年01月19日
 * @reviewer
 */
public class ProductIdxIdentityVo implements Serializable {
    private static final long serialVersionUID = 5594156877710815875L;

    /**
     * 商品编码(必传)
     */
    private String productCode;

    /**
     * 商品类型
     */
    public Integer productType;

    /**
     * 门店编码(B类商品必传, 因为价格发生变化触发的索引除外)
     */
    private String depotCode;

    /**
     * 省仓门店编码(B类商品必传, 因为价格发生变化触发的索引除外)
     */
    private String WarehouseDepotCode;

    /**
     * 区域编码(因为价格发生变化B类商品构建索引必传)
     */
    private String areaNo;

    /**
     * 是否需要被删除
     */
    private Boolean willBeDeleted = false;

    /**
     * 商品上下架状态
     */
    private SaleStatusEnum saleStatus;

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getWarehouseDepotCode() {
        return WarehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        WarehouseDepotCode = warehouseDepotCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Boolean getWillBeDeleted() {
        return willBeDeleted;
    }

    public void setWillBeDeleted(Boolean willBeDeleted) {
        this.willBeDeleted = willBeDeleted;
    }
}
