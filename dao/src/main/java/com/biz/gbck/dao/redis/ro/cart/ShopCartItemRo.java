package com.biz.gbck.dao.redis.ro.cart;

import com.biz.redis.annotation.FieldSortedSet;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * 购物车ro对象
 *
 * @author lei
 * @date 2017/04/24
 */
@Ro(key = "cart")
public class ShopCartItemRo extends BaseRedisObject<String> {

    private static final long serialVersionUID = -2165506334491450615L;

    //userId:productCode
    private String id;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 是否选中
     */
    private boolean selected;

    /**
     * 用户Id
     */
    @FieldSortedSet(key = "userId", score = "updateTimestamp")
    private Long userId;

    /**
     * 数量
     */
    private Integer quantity;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
