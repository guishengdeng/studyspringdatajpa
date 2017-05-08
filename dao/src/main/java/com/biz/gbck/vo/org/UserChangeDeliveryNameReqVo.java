package com.biz.gbck.vo.org;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;

/**
 * Created by defei on 3/16/16.
 */
public class UserChangeDeliveryNameReqVo extends CommonReqVoBindUserId {

    /**
     * 收货人姓名
     */
    private String deliveryName;

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

}
