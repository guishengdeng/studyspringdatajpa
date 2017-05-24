package com.biz.gbck.vo.voucher;


import java.util.List;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.vo.order.resp.OrderItemRespVo;

public class VoucherReqVo extends CommonReqVoBindUserId {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6858746275765140237L;
	private List<OrderItemRespVo> orderItemVos;

    public VoucherReqVo() {

    }

    public List<OrderItemRespVo> getOrderItemVos() {
        return orderItemVos;
    }

    public void setItems(List<OrderItemRespVo> orderItemVos) {
        this.orderItemVos = orderItemVos;
    }
}
