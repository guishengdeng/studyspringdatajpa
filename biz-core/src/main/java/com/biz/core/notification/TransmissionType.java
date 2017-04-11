package com.biz.core.notification;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 推送跳转行为定义
 *
 * @author david-liu
 * @date 2016年10月08日
 * @reviewer
 * @see
 */
public enum TransmissionType implements EnumerableValue {

    REDIRECT_URL(1), REDIRECT_APP_VIEW(2);

    public static class Converter extends BaseEnumValueConverter<TransmissionType> {
    }

    TransmissionType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private int value;
}
