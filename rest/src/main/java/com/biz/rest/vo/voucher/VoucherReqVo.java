package com.biz.rest.vo.voucher;


import java.util.List;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.rest.vo.order.OrderItemVo;

public class VoucherReqVo extends CommonReqVoBindUserId {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6858746275765140237L;
	private List<OrderItemVo> orderItemVos;

    public VoucherReqVo() {

    }

    public List<OrderItemVo> getOrderItemVos() {
        return orderItemVos;
    }

    public void setItems(List<OrderItemVo> orderItemVos) {
        this.orderItemVos = orderItemVos;
    }
}
