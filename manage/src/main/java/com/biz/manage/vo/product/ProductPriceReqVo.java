package com.biz.manage.vo.product;

import java.io.Serializable;
/**
 * 更新价格页面回传实体
 * @author xs
 *
 */
public class ProductPriceReqVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -4991223385635372254L;
    private String id;
    private Integer price;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "ProductPriceReqVo [id=" + id + ", price=" + price + "]";
    }
}
