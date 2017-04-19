package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * B 类商品库存 Vo
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 * @see
 */
public class TypeBProductStockVo implements Serializable {
    private static final long serialVersionUID = 4255207849165746058L;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 门店库存
     */
    private Integer depotStock = 0;

    /**
     * 全省库存
     */
    private Integer provinceStock = 0;

    public Integer getDepotStock() {
        return depotStock;
    }

    public void setDepotStock(Integer depotStock) {
        this.depotStock = depotStock;
    }

    public Integer getProvinceStock() {
        return provinceStock;
    }

    public void setProvinceStock(Integer provinceStock) {
        this.provinceStock = provinceStock;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("depotCode", depotCode)
                .append("depotStock", depotStock)
                .append("provinceStock", provinceStock)
                .toString();
    }
}
