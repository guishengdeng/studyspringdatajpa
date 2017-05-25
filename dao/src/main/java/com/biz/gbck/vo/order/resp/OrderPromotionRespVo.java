package com.biz.gbck.vo.order.resp;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;

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
    private String title;

    /**
     * 消息
     */
    private String message;

    //TODO  促销类型

    /**
     * 是否可用
     */
    private Boolean available;

    // 本促销是否与优惠卷冲突
    private boolean useCoupon = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

