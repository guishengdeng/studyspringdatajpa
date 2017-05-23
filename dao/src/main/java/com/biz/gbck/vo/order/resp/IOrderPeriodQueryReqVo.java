package com.biz.gbck.vo.order.resp;

import java.util.List;

/**
 * 下单阶段各种请求vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public interface IOrderPeriodQueryReqVo {

    //用户id
    Long getUserId();

    //订单总金额
    Integer getOrderAmount();

    //商品明细
    List<? extends IProduct> getProducts();



}
