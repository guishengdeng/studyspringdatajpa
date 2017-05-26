package com.biz.gbck.vo.order.resp;

import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.enums.product.ProductShowStatus;

import java.io.Serializable;

/**
 * 订单列表明细Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderItemRespVo extends ProductInfoVo implements Comparable<OrderItemRespVo>, Serializable {

    private static final long serialVersionUID = -7211038111581086451L;

    public OrderItemRespVo() {
        super();
    }

    public OrderItemRespVo(OrderItem orderItem) {
        this();
        this.setId(orderItem.getId());
        this.setProductId(orderItem.getProductId());
        this.setProductCode(orderItem.getProductCode());
        this.setName(orderItem.getName());
        this.setLogo(orderItem.getLogo());
        this.setSalePrice(orderItem.getSalePrice());
        this.setQuantity(orderItem.getQuantity());
        this.setCategoryId(orderItem.getCategoryId());
    }

    public boolean canBuy() {
        return this.status != null && this.status == ProductShowStatus.NORMAL.getValue();
    }


    @Override
    public int compareTo(OrderItemRespVo o) {
        return super.productCode != null && o.productCode != null ? this.productCode.compareTo(o.productCode) : this
                .productCode != null ? 1 : -1;
    }
}
