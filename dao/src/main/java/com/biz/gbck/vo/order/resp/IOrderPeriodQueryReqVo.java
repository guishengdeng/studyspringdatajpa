package com.biz.gbck.vo.order.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 下单阶段各种请求vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public interface IOrderPeriodQueryReqVo extends Serializable {

    //用户id
    Long getUserId();

    //订单总金额
    Integer getOrderAmount();

    //商品明细
    List<? extends IProduct> getProducts();

    //支付方式
    Integer getPaymentType();

    //所选优惠券类型id集合
    List<Long> getCoupons();

    Long getOrderId();



}
