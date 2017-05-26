package com.biz.soa.order.builder;

import com.biz.core.asserts.SystemAsserts;
import com.biz.gbck.vo.order.resp.OrderItemRespVo;
import com.biz.gbck.vo.order.resp.OrderPaymentTypeRespVo;
import com.biz.gbck.vo.order.resp.OrderPromotionRespVo;
import com.biz.gbck.vo.order.resp.OrderSettlePageRespVo;
import com.biz.gbck.vo.org.UserInfoVo;
import com.biz.soa.order.util.OrderUtil;
import org.codelogger.utils.ValueUtils;

import java.util.List;

/**
 * 订单结算返回vo Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageRespVoBuilder {

    private OrderSettlePageRespVo respVo;

    public static OrderSettlePageRespVoBuilder createBuilder() {
        OrderSettlePageRespVoBuilder builder = new OrderSettlePageRespVoBuilder();
        builder.respVo = new OrderSettlePageRespVo();
        return builder;
    }

    //明细&订单总金额
    public OrderSettlePageRespVoBuilder setItems(List<OrderItemRespVo> items){
        this.respVo.setItems(items);
        this.respVo.setOrderAmount(OrderUtil.calcOrderAmount(items));
        return this;
    }

    //运费
    public OrderSettlePageRespVoBuilder setFreight(Integer freight){
        this.respVo.setFreight(freight);
        return this;
    }

    //优惠券抵扣金额
    public OrderSettlePageRespVoBuilder setVoucherAmount(Integer voucherAmount){
        //TODO
        this.respVo.setVoucherAmount(voucherAmount);
        return this;
    }

    //优惠活动减免金额
    public OrderSettlePageRespVoBuilder setFreeAmount(Integer freeAmount){
        //TODO
        this.respVo.setFreeAmount(freeAmount);
        return this;
    }

    //付款促销活动
    public OrderSettlePageRespVoBuilder setPromotions(List<OrderPromotionRespVo> promotions){
        //TODO
        this.respVo.setPromotions(promotions);
        return this;
    }

    //优惠券数量
    public OrderSettlePageRespVoBuilder setCoupons(Integer couponNum){
        this.respVo.setCoupons(couponNum);
        return this;
    }

    //支付方式
    public OrderSettlePageRespVoBuilder setPaymentTypes(List<OrderPaymentTypeRespVo> paymentTyps){
        this.respVo.setPaymentTypes(paymentTyps);
        return this;
    }

    //收货人信息
    public OrderSettlePageRespVoBuilder setBuyerInfo(UserInfoVo userInfoVo){
        this.respVo.setBuyerName(userInfoVo.getUsableDeliveryName());
        this.respVo.setBuyerMobile(userInfoVo.getDeliveryMobile());
        this.respVo.setBuyerAddress(userInfoVo.getDeliveryAddress());
        this.respVo.setUserInfoVo(userInfoVo);
        return this;
    }

    public OrderSettlePageRespVo build() {
        SystemAsserts.notEmpty(respVo.getItems(), "结算商品明细为空");
        SystemAsserts.notNull(respVo.getBuyerName(), "收货人信息为空");
        SystemAsserts.notNull(respVo.getBuyerMobile(), "收货人电话为空");
        SystemAsserts.notNull(respVo.getBuyerAddress(), "收货人地址为空");
        this.calcPayAmount();
        return this.respVo;
    }

    //计算支付金额
    private void calcPayAmount() {
        Integer payAmount = this.respVo.getOrderAmount() - getAmountValue(this.respVo.getFreeAmount()) - getAmountValue(this.respVo.getVoucherAmount()) - getAmountValue(this
                .respVo.getFreight());
        this.respVo.setPayAmount(payAmount);
    }

    private static int getAmountValue(Number number) {
        int value = ValueUtils.getValue(number).intValue();
        return value > 0 ? value : 0;
    }

}
