package com.biz.gbck.vo.product.promotion.soa.singleProduct;

import com.biz.gbck.enums.product.promotion.SingleProductPromotionTypeEnum;
import java.io.Serializable;

/**
 * 单品促销Vo抽象
 *
 * Created by david-liu on 2017/04/27 11:34.
 */
public interface IProductSingleProductPromotionVo extends Serializable {

    /**
     * 获取促销ID
     *
     * @return 促销ID
     */
    Long getPromotionId();

    /**
     * 获取单品促销的类型
     *
     * @return 单品促销类型
     */
    SingleProductPromotionTypeEnum getPromotionType();

    /**
     * 获取促销描述
     *
     * @return 获取促销描述信息
     */
    String getPromotionDescription();

    /**
     * 获取商品编码
     *
     * @return 商品编码
     */
    String getProductCode();


}
