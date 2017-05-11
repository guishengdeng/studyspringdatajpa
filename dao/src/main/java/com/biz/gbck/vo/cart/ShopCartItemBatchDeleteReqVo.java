package com.biz.gbck.vo.cart;

import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * 删除购物车明细vo
 * @author lei
 * @date 2017/01/12
 */
public class ShopCartItemBatchDeleteReqVo extends BaseRequestVo {

    private static final long serialVersionUID = -7024451311062313823L;

    /**
     * 商品Id集合
     */
    private Set<String> productIds = newHashSet();


    public Set<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<String> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
