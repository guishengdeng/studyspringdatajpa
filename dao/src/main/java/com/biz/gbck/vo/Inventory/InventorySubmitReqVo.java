package com.biz.gbck.vo.Inventory;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;

import java.util.List;

/**
 * InventoryReqVo
 *
 * @author lei
 * @date 2017年05月22日
 * @reviewer
 * @see
 */
public class InventorySubmitReqVo extends CommonReqVoBindUserId {

    //盘点单号
    private String sn;

    //盘点明细
    private List<InventorySubmitItemReqVo> items;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<InventorySubmitItemReqVo> getItems() {
        return items;
    }

    public void setItems(List<InventorySubmitItemReqVo> items) {
        this.items = items;
    }
}
