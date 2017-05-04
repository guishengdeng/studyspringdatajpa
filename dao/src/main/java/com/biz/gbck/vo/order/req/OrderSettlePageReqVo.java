package com.biz.gbck.vo.order.req;

import com.biz.gbck.vo.user.BaseRequestVo;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 订单结算请求Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderSettlePageReqVo extends BaseRequestVo {

    /**
     * 结算明细
     */
    @Size(min = 1, message = "请至少选择一个商品")
    private List<OrderSettlePageItemReqVo> items;

    public List<OrderSettlePageItemReqVo> getItems() {
        return items;
    }

    public void setItems(List<OrderSettlePageItemReqVo> items) {
        this.items = items;
    }
}
