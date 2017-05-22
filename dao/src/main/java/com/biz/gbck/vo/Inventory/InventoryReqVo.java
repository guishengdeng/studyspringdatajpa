package com.biz.gbck.vo.Inventory;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import java.sql.Timestamp;

/**
 * InventoryReqVo
 *
 * @author lei
 * @date 2017年05月22日
 * @reviewer
 * @see
 */
public class InventoryReqVo extends CommonReqVoBindUserId {

    //盘点单号
    private String sn;

    //时间
    private Timestamp createTime;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
