package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 换购商品Vo
 *
 * Created by david-liu on 2017/04/24 11:24.
 */
public class RedemptionProductVo implements Serializable {
    private static final long serialVersionUID = 4161780039540573882L;

    /**
     * 换购价
     */
    private Integer redemptionPrice;

    /**
     * 换购数量
     */
    private Integer redemptionQuantity;

    /**
     * 换购商品Id
     */
    private Long productId;

    /**
     * 换购商品编码
     */
    private String productCode;

}
