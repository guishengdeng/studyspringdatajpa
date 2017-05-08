package com.biz.gbck.vo.org;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import javax.validation.constraints.NotNull;

/**
 * Created by defei on 3/16/16.
 */
public class ShopDetailOrQualificationGetReqVo extends CommonReqVoBindUserId {


    /**
     * 店铺id
     */
    @NotNull private Long shopId;

    public Long getShopId() {
        return shopId;
    }
}
