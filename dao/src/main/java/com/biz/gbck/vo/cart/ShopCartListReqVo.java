package com.biz.gbck.vo.cart;

import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 购物车列表reqVo
 *
 * @author lei
 * @date 2017/04/22
 */
public class ShopCartListReqVo extends BaseShopCartReqVo {

    private static final long serialVersionUID = 2010073680708198043L;

    public ShopCartListReqVo() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
