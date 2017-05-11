package com.biz.gbck.vo.cart;

import com.biz.gbck.vo.user.BaseRequestVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 购物车列表reqVo
 *
 * @author lei
 * @date 2017/04/22
 */
public class ShopCartListReqVo extends BaseRequestVo {


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
