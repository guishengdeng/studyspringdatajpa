package com.biz.soa.builder;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.order.OrderInvoice;
import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.enums.order.InvoiceType;
import com.biz.gbck.vo.order.req.OrderCreateReqVo;
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
public class OrderBuilder extends AbstractOrderBuilder {

    private Order order;

    public static OrderBuilder createBuilder(OrderCreateReqVo reqVo){
        OrderBuilder builder = new OrderBuilder();
        builder.order = new Order();
        builder.order.setDescription(StringUtils.trim(reqVo.getDescription()));
        builder.order.setDescription(StringUtils.trim(reqVo.getDescription()));

        builder.order.setInvoice(createInvoice(reqVo));
        return builder;
    }

    //订单明细&总金额
    public OrderBuilder setItems(List<OrderItem> orderItems) {
        this.order.setItems(orderItems);
        this.order.setOrderAmount(super.calcOrderAmount(orderItems));
        return this;

    }

    //促销优惠免额
    public OrderBuilder setFreeAmount(Integer freeAmount) {
        this.order.setFreeAmount(Math.max(ValueUtils.getValue(freeAmount), 0));
        return this;
    }

    //优惠券免额
    public OrderBuilder setVoucherAmount(Integer voucherAmount){
        order.setVoucherFreeAmount(voucherAmount);
        return this;
    }

    //运费
    public OrderBuilder setFreight(Integer freightAmount){
        order.setFreightAmount(freightAmount);
        return this;
    }

    //过期时间
    public OrderBuilder setExpireTime(Timestamp expireTime){
        order.setExpireTimestamp(expireTime);
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
        //TODO valid
        order.setId(id);
        order.setOrderCode(orderCode);
        return order;
    }




}
