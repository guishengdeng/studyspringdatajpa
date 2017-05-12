package com.biz.soa.builder;

import com.biz.gbck.vo.order.resp.OrderItemRespVo;
import com.biz.gbck.vo.order.resp.OrderPromotionRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;

import java.util.List;

/**
 * 订单结算返回vo Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageRespVoBuilder extends AbstractOrderBuilder {

    private OrderSettlePageRespVo respVo;

    public static OrderSettlePageRespVoBuilder createBuilder() {
        OrderSettlePageRespVoBuilder builder = new OrderSettlePageRespVoBuilder();
        builder.respVo = new OrderSettlePageRespVo();
        return builder;
    }

    //运费
    public OrderSettlePageRespVoBuilder setFreight(Integer freight){
        this.respVo.setFreight(freight);
        return this;
    }

    //明细&订单总金额
    public OrderSettlePageRespVoBuilder setItems(List<OrderItemRespVo> items){
        this.respVo.setItems(items);
        this.respVo.setOrderAmount(super.calcOrderAmount(items));
        return this;
    }

    //付款促销活动
    public OrderSettlePageRespVoBuilder setPromtions(List<OrderPromotionRespVo> promotions){
        this.respVo.setPromotions(promotions);
        return this;
    }

    //优惠券数量
    public OrderSettlePageRespVoBuilder setCoupons(Integer couponNum){
        this.respVo.setCoupons(couponNum);
        return this;
    }

    //支付方式
    public OrderSettlePageRespVoBuilder setPaymentTyps(List<Integer> paymentTyps){
        this.respVo.setPaymentTypes(paymentTyps);
        return this;
    }

    //收货人信息
    public OrderSettlePageRespVoBuilder setBuyerInfo(String buyerName, String buyerMobile, String buyerAddress){
        this.respVo.setBuyerName(buyerName);
        this.respVo.setBuyerMobile(buyerMobile);
        this.respVo.setBuyerAddress(buyerAddress);
        return this;
    }

    public OrderSettlePageRespVo build() {
        return this.respVo;
    }

}
