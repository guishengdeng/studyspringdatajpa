package com.depotnextdoor.core.model;

import com.google.common.base.Function;

public class String2LongTransformationFunction implements Function<String, Long> {
    @Override
    public Long apply(String s) {
        return Long.valueOf(s);
    }
}
