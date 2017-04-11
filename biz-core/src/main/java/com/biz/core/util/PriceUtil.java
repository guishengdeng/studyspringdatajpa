package com.biz.core.util;

import java.math.BigDecimal;

/**
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年9月3日
 */
public class PriceUtil {
    //表示数字0的big decimal
    public static final BigDecimal ZERO = new BigDecimal(0);

    //用于转换分和元的系数
    private static final BigDecimal _100 = new BigDecimal(100);

    /**
     * 将单位分转换为单位元
     *
     * @author yanweijin
     * @date 2016年9月3日
     */
    public static BigDecimal cent2yuan(Integer cent) {
        return new BigDecimal(cent).divide(_100);
    }

    /**
     * 字符型价格转换成integer
     *
     * @author Nian.Li <br>2016年10月9日
     */
    public static Integer getInteger(String source, Integer defaultValue) {
        return source == null ? defaultValue : Integer.valueOf(source);
    }

    /**
     * 字符转换成integer
     *
     * @author Nian.Li <br>2016年10月9日
     */
    public static Integer getInteger(String source) {
        if (source != null && !"".equals(source)) {
            return Double.valueOf(source).intValue();
        }
        return null;
    }

    /**
     * 两个价格比较，取最低价
     *
     * @param source 比较的原价格
     * @param defaultValue 默认价格
     * @param salePrice 要比较的价格
     * @return 最低价格
     * @author Nian.Li <br>2016年10月9日
     */
    public static Integer getInteger(String source, Integer defaultValue, Integer salePrice) {
        defaultValue = defaultValue == null ? -1 : defaultValue;
        Integer price = source == null ? defaultValue : Double.valueOf(source).intValue();
        salePrice = salePrice == null ? -1 : salePrice;

        if (price < 0) {
            return salePrice;
        }

        if (price > salePrice) {
            price = salePrice;
        }
        return price;
    }
}
