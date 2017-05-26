package com.biz.gbck.vo.cart;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 购物车数量请求vo
 *
 * @author lei
 * @date 2017/04/05
 */
public class ShopCartNumReqVo extends CommonReqVoBindUserId {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
