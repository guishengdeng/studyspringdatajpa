package com.biz.gbck.vo.product.promotion;

import com.biz.gbck.enums.product.promotion.PromotionTypeEnum;
import java.io.Serializable;

/**
 * 商品促销
 *
 * Created by david-liu on 2017/04/27 15:20.
 */
public interface IProductPromotionVo extends Serializable {

    /**
     * 获取促销类型
     *
     * @return 促销类型枚举
     */
    PromotionTypeEnum getPromotionType();

    /**
     * 获取促销描述
     *
     * @return 促销描述信息
     */
    String getDescription();

    /**
     * 获取促销Id
     *
     * @return 促销ID
     */
    Long getPromotionId();

    /**
     * 获取减免金额
     *
     * @return 促销减免金额
     */
    Integer getCutAccount();

}
