package com.biz.gbck.vo.order.resp;

import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.vo.user.BaseRequestVo;

import java.util.List;

/**
 * 订单结算请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageRespVo extends BaseRequestVo {

    private static final long serialVersionUID = -8415628255360674537L;

    /**
     * 总金额
     */
    private Integer orderAmount;

    // 运费
    private Integer freight = 0;

    /**
     * 商品明细
     */
    private List<OrderItemRespVo> items;

    //TODO 促销列表

    // 可用优惠卷数量
    private Integer coupons = 0;

    /**
     * 可用支付方式 {@link PaymentType}
     */
    private List<Integer> paymentTypes;

    /**
     * 是否有效
     */
    private boolean valid = true;

    /**
     * 卖家名称
     */
    private String sellerName;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerMobile;

    /**
     * 买家收货地址
     */
    private String buyerAddress;
}

