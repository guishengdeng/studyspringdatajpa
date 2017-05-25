package com.biz.gbck.vo.order.resp;

import java.io.Serializable;

/**
 * 商品接口Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public interface IProduct extends Serializable {

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

    /**
     * 分类Id
     */
    Long getCategoryId();


}
