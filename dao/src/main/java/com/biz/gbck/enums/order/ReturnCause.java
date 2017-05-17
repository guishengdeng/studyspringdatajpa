package com.biz.gbck.enums.order;


import com.biz.core.enums.EnumerableValue;
import com.biz.core.enums.converter.BaseEnumValueConverter;

/**
 * 售后原因
 *
 * @author lei
 * @date 2017年04月20日
 * @reviewer
 * @see
 */
public enum ReturnCause implements EnumerableValue {

    OTHER(0, "其他"),
    PRE_EXPIRED(1, "临期"),
    BROKEN(2, "破损"),
    QUALITY_PROBLEM(3, "质量问题");



    public final int value;

    public final String desc;

    public static class Converter extends BaseEnumValueConverter<ReturnCause> {
    }

    ReturnCause(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ReturnCause valueOf(Integer value) {
       if (value == PRE_EXPIRED.getValue()) {
           return PRE_EXPIRED;
       } else if (value == BROKEN.getValue()) {
           return BROKEN;
       } else if (value == QUALITY_PROBLEM.getValue()){
           return QUALITY_PROBLEM;
       } else {
           return OTHER;
       }
    }


    @Override
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
