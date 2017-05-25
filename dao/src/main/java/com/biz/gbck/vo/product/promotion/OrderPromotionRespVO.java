package com.biz.gbck.vo.product.promotion;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单促销ResponseVO
 *
 * Created by david-liu on 2017/05/24 12:10.
 */
public class OrderPromotionRespVO extends CommonReqVoBindUserId {
    private static final long serialVersionUID = 7022028895712736238L;

    /**
     * 促销减免的订单金额
     */
    private Integer promotionCutOrderAmount;

    /**
     * 订单促销减免之前的金额
     */
    private Integer orderOriginAmount;

    /**
     * 订单总金额
     */
    private Integer orderAmount;

    /**
     * 订单商品项促销明细
     */
    private List<OrderActivePromotionItemVO> activePromotionItems;

    public Integer getPromotionCutOrderAmount() {
        return promotionCutOrderAmount;
    }

    public void setPromotionCutOrderAmount(Integer promotionCutOrderAmount) {
        this.promotionCutOrderAmount = promotionCutOrderAmount;
    }

    public Integer getOrderOriginAmount() {
        return orderOriginAmount;
    }

    public void setOrderOriginAmount(Integer orderOriginAmount) {
        this.orderOriginAmount = orderOriginAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<OrderActivePromotionItemVO> getActivePromotionItems() {
        return activePromotionItems;
    }

    public void setActivePromotionItems(List<OrderActivePromotionItemVO> activePromotionItems) {
        this.activePromotionItems = activePromotionItems;
    }

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time.getDayOfWeek().getValue());
    }
}
