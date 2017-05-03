package com.biz.soa.product.service.interfaces.impl.bbc;

import com.biz.gbck.dao.redis.ro.product.bbc.PriceRo;
import com.biz.gbck.vo.depot.DepotPromotionVo;
import com.biz.soa.product.service.interfaces.bbc.ProductPriceGenerator;
import java.util.Date;
import org.codelogger.utils.ValueUtils;

/**
 * B类商品价格生成器
 *
 * @author david-liu
 * @date 2017年02月14日
 * @reviewer
 */
public class TypeBProductPriceGenerator implements ProductPriceGenerator {

    private PriceRo priceRo;
    private DepotPromotionVo depotPromotionVo;

    private TypeBProductPriceGenerator(PriceRo priceRo, DepotPromotionVo depotPromotionVo) {
        this.priceRo = priceRo;
        this.depotPromotionVo = depotPromotionVo;
    }

    public static ProductPriceGenerator doClone(PriceRo priceRo, DepotPromotionVo depotPromotionVo) {
        return new TypeBProductPriceGenerator(priceRo, depotPromotionVo);
    }

    @Override
    public Integer doGetFinalPrice(Integer userLevel) {
        // 如果门店价格为空, 不允许结算
        if (this.priceRo == null) {
            return null;
        }

        Integer userLevelPrice = this.doGetFinalPriceAfterValidate(userLevel);
        if (userLevelPrice == null) {
            return null;
        }

        Date now = new Date();
        Integer promotionPrice = null;
        if (depotPromotionVo != null && depotPromotionVo.getPromotionStartTime().before(now) && depotPromotionVo.getPromotionEndTime().after(now)) {
            promotionPrice = depotPromotionVo.getPromotionPrice();
        }

        if (promotionPrice == null) {
            return userLevelPrice;
        } else {
            Integer priceBound = this.priceRo.getDepotPromotionPriceBound();
            promotionPrice = Math.max(promotionPrice, priceBound);
            return Math.min(promotionPrice, userLevelPrice);
        }
    }

    @Override
    public Integer doGetMarketPrice() {
        if (this.priceRo == null) {
            return null;
        }

        return this.priceRo.getMarketPrice();
    }

    /**
     * 将B类商品的结算价格(会员价)过滤放到这个方法
     *
     * @param userLevel 用户等级
     * @return 结算价格
     */
    private Integer doGetFinalPriceAfterValidate(Integer userLevel) {
        // 最低限价，成本价不可能同时为0（如果存在，则不允许上架及结算）
        if (ValueUtils.getValue(this.priceRo.getMinPrice()) <= 0 && ValueUtils.getValue(this.priceRo.getCostPrice()) <= 0) {
            return null;
        }

        // 成本价小于等于0, 不允许结算
        if (ValueUtils.getValue(this.priceRo.getCostPrice()) <= 0) {
            return null;
        }

        Integer userLevelPrice = this.priceRo.getPriceByLevel(userLevel);
        // 会员价为空, 取1级会员价
        if (userLevelPrice == null) {
            // 如果1级会员价为空, 不允许结算
            if (userLevel == 1) {
                return null;
            }
            // 1级以上的会员价为空, 取1级会员价, 如果会员价还为空, 不允许结算
            userLevelPrice = this.priceRo.getPriceByLevel(1);
            if (userLevelPrice == null) {
                return null;
            }
        }

        if (ValueUtils.getValue(this.priceRo.getMinPrice()) <= 0) {
            // 最低限价小于等于0, 会员价和成本价取高
            return Math.max(userLevelPrice, this.priceRo.getCostPrice());
        } else {
            return userLevelPrice > this.priceRo.getMinPrice() ? userLevelPrice : this.priceRo.getMinPrice();
        }

    }
}
