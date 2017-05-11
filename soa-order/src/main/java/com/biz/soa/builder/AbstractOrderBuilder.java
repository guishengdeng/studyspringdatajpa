package com.biz.soa.builder;

import com.biz.gbck.vo.order.resp.IProduct;
import org.codelogger.utils.ValueUtils;

import java.util.List;

/**
 * 订单抽象 Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class AbstractOrderBuilder {

    Integer calcOrderAmount(List<? extends IProduct> productVos) {
        int orderAmount = 0;
        for (IProduct productVo : productVos) {
            orderAmount += ValueUtils.getValue(productVo.getPrice()) * ValueUtils.getValue(productVo.getQuantity());
        }
        return orderAmount;

    }
}
