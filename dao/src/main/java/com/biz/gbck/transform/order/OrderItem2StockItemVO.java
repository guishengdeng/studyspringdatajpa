package com.biz.gbck.transform.order;

import com.biz.gbck.dao.mysql.po.order.OrderItem;
import com.biz.gbck.vo.stock.StockItemVO;
import com.google.common.base.Function;

/**
 * OrderItem -> StockItemVO
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
public class OrderItem2StockItemVO implements Function<OrderItem, StockItemVO> {

    /**
     * 是否减去
     */
    private boolean reduce = false;

    public OrderItem2StockItemVO(boolean reduce) {
        this.reduce = reduce;
    }

    @Override
    public StockItemVO apply(OrderItem input) {
        if (input != null) {
            StockItemVO itemVo = new StockItemVO();
            itemVo.setProductId(input.getProductId());
            Integer quantity = input.getQuantity();
            quantity = reduce ? -quantity :quantity;
            itemVo.setQuantity(quantity);
            return itemVo;
        }
        return null;
    }
}
