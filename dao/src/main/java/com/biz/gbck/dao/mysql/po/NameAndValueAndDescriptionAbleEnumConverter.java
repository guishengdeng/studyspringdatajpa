package com.biz.gbck.dao.mysql.po;


import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;

import javax.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Created by defei on 5/1/16.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class NameAndValueAndDescriptionAbleEnumConverter<T extends NameAndValueAndDescriptionAbleEnum>
    implements AttributeConverter<NameAndValueAndDescriptionAbleEnum, Integer> {

    private Class<T> tClass = null;

    private Class<T> getTargetClass() {

        if (tClass == null) {
            Type[] actualTypeArguments =
                ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments();
            tClass = actualTypeArguments == null || actualTypeArguments.length == 0 ?
                null :
                (Class<T>) actualTypeArguments[0];
        }
        return tClass;
    }

    @Override public Integer convertToDatabaseColumn(NameAndValueAndDescriptionAbleEnum attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override public T convertToEntityAttribute(Integer dbData) {

        Class<? extends NameAndValueAndDescriptionAbleEnum> fieldClass = getTargetClass();
        if (fieldClass == null || !fieldClass.isEnum()) {
            return null;
        }
        for (NameAndValueAndDescriptionAbleEnum enumConstant : fieldClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.getValue(), dbData)) {
                return (T) enumConstant;
            }
        }
        return null;
    }
}
