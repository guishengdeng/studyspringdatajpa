package com.biz.gbck.vo.Inventory;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 盘点单返回结果
 *
 * @author lei
 * @date 2017年05月22日
 * @reviewer
 * @see
 */
public class InventoryRespVo implements Serializable {

    private static final long serialVersionUID = 3487218651873943354L;

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
