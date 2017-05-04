package com.biz.gbck.vo.order;

/**
 * IProductVo
 *
 * @author lei
 * @date 2017年05月04日
 * @reviewer
 * @see
 */
public interface IProductVo {
    /**
     * 商品
     * @author bruce.qin
     * @date 2016年8月22日
     * @return
     */
    Long getProductCode();

    /**
     * 价格
     */
    Integer getPrice();

    /**
     * 数量
     */
    Integer getQuantity();
}
