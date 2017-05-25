package com.biz.rest.vo.order;

import com.biz.gbck.common.model.order.IOrderItemVo;

public class OrderItemVo implements IOrderItemVo {

	public Long productId;
	public String name;
	public int quantity;
	public String logo;
	public int price;

	@Override
	public Long getProductId() {
		return productId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String getLogo() {
		return logo;
	}

	@Override
	public int getPrice() {
		return price;
	}
}
