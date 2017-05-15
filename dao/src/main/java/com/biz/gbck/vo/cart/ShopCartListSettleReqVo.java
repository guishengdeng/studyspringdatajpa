package com.biz.gbck.vo.cart;

import com.biz.gbck.vo.order.req.ProductItemReqVo;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 购物车列表reqVo(结算)
 *
 * @author lei
 * @date 2017/04/22
 */
public class ShopCartListSettleReqVo extends ShopCartListReqVo {

    private static final long serialVersionUID = -8166161728732416354L;

    /**
     * 优惠活动id
     */
    private Long promotionId;

    /**
     * 优惠券
     */
    private List<Long> usedCoupons = newArrayList();

    /**
     * 结算商品明细
     */
    private List<ProductItemReqVo> items = newArrayList();;

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public List<Long> getUsedCoupons() {
        return usedCoupons;
    }

    public void setUsedCoupons(List<Long> usedCoupons) {
        this.usedCoupons = usedCoupons;
    }

    public List<ProductItemReqVo> getItems() {
        return items;
    }

    public void setItems(List<ProductItemReqVo> items) {
        this.items = items;
    }
}
