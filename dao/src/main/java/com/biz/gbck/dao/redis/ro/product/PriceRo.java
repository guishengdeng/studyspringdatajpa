package com.biz.gbck.dao.redis.ro.product;

import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codelogger.utils.ValueUtils;

/**
 * 价格 Ro(ID 使用商品编码 + 区域 Code)
 *
 * @author david-liu
 * @date 2016年12月30日
 * @reviewer
 * @see
 */
@Ro(key = "product:PriceRo")
public class PriceRo extends BaseRedisObject<String> implements Serializable {

    private static final long serialVersionUID = 1533330550754233842L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 销售价
     */
    private Integer finalPrice;

    /**
     * 市场价
     */
    private Integer marketPrice;

    /**
     * 成本价
     */
    private Integer costPrice;

    /**
     * 最低限价
     */
    private Integer minPrice;

    /**
     * 1级会员价
     */
    private Integer price1;

    /**
     * 2级会员价
     */
    private Integer price2;

    /**
     * 3级会员价
     */
    private Integer price3;

    /**
     * 4级会员价
     */
    private Integer price4;

    /**
     * 5级会员价
     */
    private Integer price5;

    /**
     * 6级会员价
     */
    private Integer price6;

    /**
     * 7级会员价
     */
    private Integer price7;

    /**
     * 8级会员价
     */
    private Integer price8;

    /**
     * 9级会员价
     */
    private Integer price9;

    /**
     * 10级会员价
     */
    private Integer price10;

    /**
     * 11级会员价
     */
    private Integer price11;

    /**
     * 12级会员价
     */
    private Integer price12;

    /**
     * 13级会员价
     */
    private Integer price13;

    /**
     * 14级会员价
     */
    private Integer price14;

    /**
     * 15级会员价
     */
    private Integer price15;

    /**
     * 16级会员价
     */
    private Integer price16;

    /**
     * 17级会员价
     */
    private Integer price17;

    /**
     * 18级会员价
     */
    private Integer price18;

    /**
     * 19级会员价
     */
    private Integer price19;

    /**
     * 20级会员价
     */
    private Integer price20;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    public Integer getPrice3() {
        return price3;
    }

    public void setPrice3(Integer price3) {
        this.price3 = price3;
    }

    public Integer getPrice4() {
        return price4;
    }

    public void setPrice4(Integer price4) {
        this.price4 = price4;
    }

    public Integer getPrice5() {
        return price5;
    }

    public void setPrice5(Integer price5) {
        this.price5 = price5;
    }

    public Integer getPrice6() {
        return price6;
    }

    public void setPrice6(Integer price6) {
        this.price6 = price6;
    }

    public Integer getPrice7() {
        return price7;
    }

    public void setPrice7(Integer price7) {
        this.price7 = price7;
    }

    public Integer getPrice8() {
        return price8;
    }

    public void setPrice8(Integer price8) {
        this.price8 = price8;
    }

    public Integer getPrice9() {
        return price9;
    }

    public void setPrice9(Integer price9) {
        this.price9 = price9;
    }

    public Integer getPrice10() {
        return price10;
    }

    public void setPrice10(Integer price10) {
        this.price10 = price10;
    }

    public Integer getPrice11() {
        return price11;
    }

    public void setPrice11(Integer price11) {
        this.price11 = price11;
    }

    public Integer getPrice12() {
        return price12;
    }

    public void setPrice12(Integer price12) {
        this.price12 = price12;
    }

    public Integer getPrice13() {
        return price13;
    }

    public void setPrice13(Integer price13) {
        this.price13 = price13;
    }

    public Integer getPrice14() {
        return price14;
    }

    public void setPrice14(Integer price14) {
        this.price14 = price14;
    }

    public Integer getPrice15() {
        return price15;
    }

    public void setPrice15(Integer price15) {
        this.price15 = price15;
    }

    public Integer getPrice16() {
        return price16;
    }

    public void setPrice16(Integer price16) {
        this.price16 = price16;
    }

    public Integer getPrice17() {
        return price17;
    }

    public void setPrice17(Integer price17) {
        this.price17 = price17;
    }

    public Integer getPrice18() {
        return price18;
    }

    public void setPrice18(Integer price18) {
        this.price18 = price18;
    }

    public Integer getPrice19() {
        return price19;
    }

    public void setPrice19(Integer price19) {
        this.price19 = price19;
    }

    public Integer getPrice20() {
        return price20;
    }

    public void setPrice20(Integer price20) {
        this.price20 = price20;
    }

    public Integer getPriceByLevel(Integer userLevel) {
        Class<PriceRo> klass = PriceRo.class;
        Integer price;
        try {
            Integer level = (userLevel == null || userLevel > 20 || userLevel < 1) ? 1 : userLevel;
            Method priceMethod = klass.getMethod(String.format("getPrice%d", level));
            price = (Integer) priceMethod.invoke(this);
            if (price == null || price == 0) {
                price = this.getPrice1();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            price = this.getPrice1();
        }

        return price;
    }

    /**
     * 根据PriceRo 获取B类商品门店特价的下界
     * <pre>
     *     商品的简单特价不能低于成本价和最低限价中较高的价格
     * </pre>
     *
     * @return 商品门店特价的下界
     */
    public Integer getDepotPromotionPriceBound() {
        if (ValueUtils.getValue(this.getCostPrice()) <= 0) {
            return null;
        } else {
            if (ValueUtils.getValue(this.getMinPrice()) <= 0) {
                return this.getCostPrice();
            } else {
                return Math.max(this.getCostPrice(), this.getMinPrice());
            }
        }
    }

    @Override
    public String toString() {
        return "PriceRo{" +
                "productCode='" + productCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", finalPrice=" + finalPrice +
                ", marketPrice=" + marketPrice +
                ", costPrice=" + costPrice +
                ", minPrice=" + minPrice +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", price3=" + price3 +
                ", price4=" + price4 +
                ", price5=" + price5 +
                ", price6=" + price6 +
                ", price7=" + price7 +
                ", price8=" + price8 +
                ", price9=" + price9 +
                ", price10=" + price10 +
                ", price11=" + price11 +
                ", price12=" + price12 +
                ", price13=" + price13 +
                ", price14=" + price14 +
                ", price15=" + price15 +
                ", price16=" + price16 +
                ", price17=" + price17 +
                ", price18=" + price18 +
                ", price19=" + price19 +
                ", price20=" + price20 +
                '}';
    }
}
