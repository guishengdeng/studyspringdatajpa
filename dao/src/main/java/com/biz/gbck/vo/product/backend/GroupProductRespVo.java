package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.SaleStatusEnum;
import java.io.Serializable;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/3/27
 */
public class GroupProductRespVo implements Serializable {

    private static final long serialVersionUID = 4765762589899828791L;

    private String name;

    private String id;

    private String price;

    private SaleStatusEnum saleStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public SaleStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }
}
