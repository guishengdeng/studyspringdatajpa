package com.biz.gbck.vo.voucher;


import java.util.List;

import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.vo.order.resp.IProduct;

public class VoucherRequestVo extends CommonReqVoBindUserId {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6858746275765140237L;
	private List<? extends IProduct> orderItemVos;

    public VoucherRequestVo() {

    }

	public List<? extends IProduct> getOrderItemVos() {
		return orderItemVos;
	}

	public void setOrderItemVos(List<? extends IProduct> orderItemVos) {
		this.orderItemVos = orderItemVos;
	}

}
