package com.biz.soa.order.builder;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderConsignee;
import com.biz.gbck.dao.mysql.po.order.OrderInvoice;
import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.enums.order.InvoiceType;
import com.biz.gbck.enums.order.OrderStatus;
import com.biz.gbck.enums.order.PaymentStatus;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.order.req.OrderCreateReqVo;
import com.biz.gbck.vo.org.UserInfoVo;
import org.apache.commons.lang.StringUtils;
import org.codelogger.utils.ValueUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单Builder
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderBuilder {

    private Order order;

    public static OrderBuilder createBuilder(OrderCreateReqVo reqVo){
        OrderBuilder builder = new OrderBuilder();
        builder.order = new Order();
        builder.order.setPayStatus(PaymentStatus.CREATE_PAYMENT);
        builder.order.setStatus(OrderStatus.PRE_PAY);
        builder.order.setDescription(StringUtils.trim(reqVo.getDescription()));
        builder.order.setInvoice(createInvoice(reqVo));
        return builder;
    }

    //用户相关信息
    public OrderBuilder setUserInfo(UserInfoVo userInfo) {
        SystemAsserts.notNull(userInfo.getUserId(), "买家Id为空");
        SystemAsserts.notNull(userInfo.getShopId(), "店铺Id为空");
        SystemAsserts.notNull(userInfo.getPartnerId(), "合伙人Id为空");

        this.order.setUserId(Long.valueOf(userInfo.getUserId()));
        this.order.setSellerId(Long.valueOf(userInfo.getPartnerId()));
        this.order.setShopId(userInfo.getShopId());
        this.order.setCompanyId(userInfo.getPlatformId());

        //收货信息
        OrderConsignee consignee = new OrderConsignee();
        consignee.setName(userInfo.getUsableDeliveryName());
        consignee.setMobile(userInfo.getDeliveryMobile());
        if (userInfo.getProvinceId() != null) {
            consignee.setProvinceId(userInfo.getProvinceId().intValue());
        }
        if (userInfo.getDistrictId() != null) {
            consignee.setDistrictId(userInfo.getDistrictId().intValue());
        }
        if (userInfo.getCityId() != null) {
            consignee.setCityId(userInfo.getCityId().intValue());
        }
        consignee.setAddress(StringUtils.trim(userInfo.getDeliveryAddress()));
        order.setConsignee(consignee);
        return this;
    }


    //订单明细&总金额&付款单详情
    public OrderBuilder setItems(List<OrderItem> orderItems) {
        this.order.setItems(orderItems);

        int orderAmount = 0;
        StringBuilder sb = new StringBuilder();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderAmount += ValueUtils.getValue(orderItem.getSalePrice()) * ValueUtils.getValue(orderItem.getQuantity());
            sb.append(orderItem.getName()).append(",");
        }

        this.order.setOrderAmount(orderAmount);
        this.order.setSubject(sb.toString());
        return this;

    }

    //促销优惠免额
    public OrderBuilder setFreeAmount(Integer freeAmount) {
        this.order.setFreeAmount(Math.max(ValueUtils.getValue(freeAmount), 0));
        return this;
    }

    //优惠券免额
    public OrderBuilder setVoucherAmount(Integer voucherAmount){
        order.setVoucherAmount(voucherAmount);
        return this;
    }

    //运费
    public OrderBuilder setFreight(Integer freight){
        order.setFreight(freight);
        return this;
    }

    //TODO 计算后实际支付金额
    //支付金额
    public OrderBuilder setPayAmount(Integer payAmount){
        order.setPayAmount(payAmount);
        return this;
    }

    //支付金额
    public OrderBuilder setPaymentType(PaymentType paymentType){
        SystemAsserts.notNull(paymentType, "订单支付方式为空");
        order.setPaymentType(paymentType);
        return this;
    }


    //发票
    private static OrderInvoice createInvoice(OrderCreateReqVo reqVo) {
        OrderInvoice orderInvoice = new OrderInvoice();
        Integer invoiceType = ValueUtils.getValue(reqVo.getInvoiceType());
        if (invoiceType == InvoiceType.PERSONAL.getValue()) {
            orderInvoice.setInvoiceType(InvoiceType.PERSONAL);
        } else if (invoiceType == InvoiceType.COMPANY.getValue()) {
            orderInvoice.setInvoiceType(InvoiceType.COMPANY);
            orderInvoice.setTitle(StringUtils.trim(reqVo.getInvoiceTitle()));
        }
        return orderInvoice;
    }

    public Order build(Long id, String orderCode){
        SystemAsserts.notNull(order);
        SystemAsserts.notNull(orderCode, "订单编码为空");
        SystemAsserts.notNull(order.getUserId(), "买家Id为空");
        SystemAsserts.notNull(order.getSellerId(), "卖家Id为空");
        SystemAsserts.notNull(order.getOrderAmount(), "订单总金额为空");
        SystemAsserts.notNull(order.getPaymentType(), "订单支付方式为空");
        SystemAsserts.notEmpty(order.getItems(), "订单明细为空");
        SystemAsserts.notNull(order.getConsignee(), "收货信息为空");

        order.setId(id);
        order.setOrderCode(orderCode);
        Timestamp createTime = DateUtil.now();
        Timestamp expireTime = new Timestamp(createTime.getTime() + DateUtil.HOUR);
        order.setCreateTimestamp(createTime);
        order.setExpireTimestamp(expireTime);
        return order;
    }




}
