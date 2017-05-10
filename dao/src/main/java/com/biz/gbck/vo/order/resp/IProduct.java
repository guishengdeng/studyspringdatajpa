package com.biz.gbck.vo.order.resp;

/**
 * 商品接口Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public interface IProduct {

    /**
     * 商品id
     */
    Long getProductId();

    /**
     * 商品价格
     */
    Integer getPrice();

    /**
     * 数量
     */
    Integer getQuantity();


}
