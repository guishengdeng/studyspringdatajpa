package com.biz.gbck.vo.order.req;

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
public class OrderSettlePageReqVo extends BaseRequestVo {

    /**
     * 优惠券id集合
     */
    private List<Long> usedCoupons;

    public List<Long> getUsedCoupons() {
        return usedCoupons;
    }

    public void setUsedCoupons(List<Long> usedCoupons) {
        this.usedCoupons = usedCoupons;
    }
}
