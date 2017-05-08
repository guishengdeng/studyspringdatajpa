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
    private Set<String> productCodes = newHashSet();


    public Set<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(Set<String> productCodes) {
        this.productCodes = productCodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
