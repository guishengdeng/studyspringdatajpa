package com.biz.gbck.vo.voucher;


import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.vo.order.req.ProductItemReqVo;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public class VoucherRequestVo extends CommonReqVoBindUserId {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6858746275765140237L;


	private List<ProductItemReqVo> orderItemVos = newArrayList();

    public VoucherRequestVo() {

    }

	public List<ProductItemReqVo> getOrderItemVos() {
		return orderItemVos;
	}

	public void setOrderItemVos(List<ProductItemReqVo> orderItemVos) {
		this.orderItemVos = orderItemVos;
	}

}
