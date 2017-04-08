package com.depotnextdoor.core.model;

import java.util.function.Function;

public class TransformationFunciton implements Function<Identifiable<Long>, Long> {
    @Override
    public Long apply(Identifiable<Long> longIdentifiable) {
        return longIdentifiable.getId();
    }
}
