package com.biz.gbck.enums.oms;

import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 中台门店促销类型
 *
 * @author zhangcheng
 * @date 2017/1/12
 * @reviewer
 * @see
 */
public enum MnsDepotPromotionType implements EnumerableValue {
    SPECIAL_OFFER(1), REACH_LIMIT_OFFER(3);

    private int value;

    MnsDepotPromotionType(int value) {
        this.value = value;
    }

    public static class Converter extends BaseEnumValueConverter<MnsDepotPromotionType> {
    }

    @Override
    public int getValue() {
        return value;
    }
}
