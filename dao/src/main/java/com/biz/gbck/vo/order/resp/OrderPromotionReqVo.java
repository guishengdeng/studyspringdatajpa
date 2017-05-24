package com.biz.gbck.vo.order.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 促销请求Vo
 *
 * @author lei
 * @date 2017年05月20日
 * @reviewer
 * @see
 */
public class OrderPromotionReqVo implements Serializable, IOrderPeriodQueryReqVo {

    private static final long serialVersionUID = -513598021053330981L;

    /**
     * 用户组ID
     */
    private Long companyGroupId;

    //用户id
    private Long userId;

    //订单总金额
    private Integer orderAmount;

    //商品明细
    private List<? extends IProduct> products;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Integer getOrderAmount() {
        return orderAmount;
    }

    @Override
    public List<? extends IProduct> getProducts() {
        return products;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setProducts(List<? extends IProduct> products) {
        this.products = products;
    }

    @Override
    public Integer getPaymentType() {
        return null;
    }

    @Override
    public List<Long> getCoupons() {
        return null;
    }

    @Override
    public Long getOrderId() {
        return null;
    }

    public Long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(Long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }
}

