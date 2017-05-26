package com.biz.gbck.vo.order.resp;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.vo.product.promotion.OrderActivePromotionItemVO;

/**
 * 促销活动返回Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderPromotionRespVo extends CommonReqVoBindUserId {

    private static final long serialVersionUID = -8415628255360674537L;

    /**
     * 活动Id
     */
    private Long id;

    /**
     * 标题
     */
    private String name;

    /**
     * 消息
     */
    private String description;

    /**
     * 是否可用
     */
    private Boolean available;

    // 本促销是否与优惠卷冲突
    private boolean useCoupon = true;

    public OrderPromotionRespVo() {
    }

    public OrderPromotionRespVo(OrderActivePromotionItemVO itemVO) {
        this();
        this.id = itemVO.getId();
        this.name = itemVO.getName();
        this.description = itemVO.getDescription();
        this.available = itemVO.getAllowVoucher();
        this.useCoupon = itemVO.getAllowVoucher();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean isUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(boolean useCoupon) {
        this.useCoupon = useCoupon;
    }
}

