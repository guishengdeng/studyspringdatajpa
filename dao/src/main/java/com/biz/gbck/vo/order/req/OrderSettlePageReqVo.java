package com.biz.gbck.vo.order.req;

import com.biz.gbck.vo.user.BaseRequestVo;

import javax.validation.constraints.Size;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单结算请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageReqVo extends BaseRequestVo {

    private static final long serialVersionUID = 6996880845265569000L;

    @Size(min = 1, message = "请选择要结算的商品")
    private List<ProductItemReqVo> items = newArrayList();

    /**
     * 优惠券id集合
     */
    private List<Long> usedCoupons;

    public List<ProductItemReqVo> getItems() {
        return items;
    }

    public void setItems(List<ProductItemReqVo> items) {
        this.items = items;
    }

    public List<Long> getUsedCoupons() {
        return usedCoupons;
    }

    public void setUsedCoupons(List<Long> usedCoupons) {
        this.usedCoupons = usedCoupons;
    }
}
