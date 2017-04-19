package com.biz.gbck.common.ro;

import com.biz.gbck.common.NameAndValueAndDescriptionAbleEnum;
import com.biz.gbck.common.ro.annotation.RedisIgnore;
import com.biz.gbck.common.ro.annotation.RedisReadIgnore;
import com.biz.gbck.common.ro.annotation.RedisWriteIgnore;
import com.biz.redis.bean.RedisObjMap;
import org.apache.commons.lang3.math.NumberUtils;
import org.codelogger.utils.JudgeUtils;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by defei on 5/13/15.
 */
public abstract class AbstractRedisObj {

    private static Logger logger = LoggerFactory.getLogger(AbstractRedisObj.class);

    public void fromMap(Map<byte[], byte[]> map) {
        RedisObjMap objMap = new RedisObjMap(map);
        List<Field> declaredFields = getReadSupportedFields(getClass());
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            Type genericType = declaredField.getGenericType();
            Object value = null;
            try {
                if (genericType == Integer.class || genericType == int.class) {
                    value = objMap.getInt(fieldName);
                } else if (genericType == Long.class || genericType == long.class) {
                    value = objMap.getLong(fieldName);
                } else if (genericType == String.class) {
                    value = objMap.getString(fieldName);
                } else if (genericType == Boolean.class || genericType == boolean.class) {
                    value = objMap.getBoolean(fieldName);
                } else if (genericType == Byte.class || genericType == byte.class) {
                    value = objMap.getByte(fieldName);
                } else if (genericType == Short.class || genericType == short.class) {
                    value = objMap.getShort(fieldName);
                } else if (genericType == BigDecimal.class) {
                    value = objMap.getBigDecimal(fieldName, 6);
                } else if (genericType == Date.class || genericType == Timestamp.class) {
                    Long time = objMap.getLong(fieldName);
                    value = time == null ? null : new Timestamp(time);
                } else if (((Class) genericType).isEnum()) {
                    String fieldValue = objMap.getString(fieldName);
                    if (StringUtils.isBlank(fieldValue)) {
                        value = null;
                    } else {
                        try {
                            value = Enum.valueOf((Class) genericType, fieldValue);
                        } catch (Exception e) {
                            if (NumberUtils.isNumber(fieldValue)
                                && NameAndValueAndDescriptionAbleEnum.class
                                .isAssignableFrom((Class) genericType)) {
                                for (Object enumConstant : ((Class) genericType)
                                    .getEnumConstants()) {
                                    if (Objects.equals(
                                        ((NameAndValueAndDescriptionAbleEnum) enumConstant)
                                            .getValue(), Integer.valueOf(fieldValue))) {
                                        value = enumConstant;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
                if (value != null) {
                    declaredField.set(this, value);
                }
            } catch (IllegalAccessException e) {
                logger.warn("Can not set {} to {}.{}", value, this.getClass().getName(), fieldName,
                    e);
            }
        }
    }

    /**
     * Include all basis data.
     */
    public Map<byte[], byte[]> toMap() {
        RedisObjMap map = new RedisObjMap();
        List<Field> declaredFields = getWriteSupportedFields(getClass());
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            Type genericType = declaredField.getGenericType();
            try {
                if (genericType == Integer.class || genericType == int.class) {
                    map.put(fieldName, (Integer) declaredField.get(this));
                } else if (genericType == Long.class || genericType == long.class) {
                    map.put(fieldName, (Long) declaredField.get(this));
                } else if (genericType == String.class) {
                    String value = (String) declaredField.get(this);
                    map.put(fieldName, value);
                } else if (genericType == Boolean.class || genericType == boolean.class) {
                    map.put(fieldName, (Boolean) declaredField.get(this));
                } else if (genericType == Byte.class || genericType == byte.class) {
                    map.put(fieldName, (Byte) declaredField.get(this));
                } else if (genericType == Short.class || genericType == short.class) {
                    map.put(fieldName, (Short) declaredField.get(this));
                } else if (genericType == BigDecimal.class) {
                    map.put(fieldName, (BigDecimal) declaredField.get(this), 6);
                } else if (genericType == Date.class || genericType == Timestamp.class) {
                    Date ts = (Date) declaredField.get(this);
                    map.put(fieldName, ts == null ? null : ts.getTime());
                } else if (((Class) genericType).isEnum()) {
                    Enum em = (Enum) declaredField.get(this);
                    map.put(fieldName, em == null ? null : em.name());
                }
            } catch (IllegalAccessException e) {
                logger.warn("Can not get value of {}.{}", this.getClass().getName(), fieldName, e);
            }
        }
        return map.getSerialMap();
    }

    private List<Field> getReadSupportedFields(Class roClass) {
        List<Field> supportedFields = supportedReadAbleFieldsMap.get(roClass);
        if (supportedFields == null) {
            synchronized (AbstractRedisObj.class) {
                supportedFields = new ArrayList<>();
                Field[] declaredFields = getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (!Modifier.isFinal(declaredField.getModifiers()) && JudgeUtils
                        .allIsNull(declaredField.getAnnotation(RedisIgnore.class),
                            declaredField.getAnnotation(RedisReadIgnore.class))) {
                        declaredField.setAccessible(true);
                        Type genericType = declaredField.getGenericType();
                        if(isSupportDataType(genericType)){
                            supportedFields.add(declaredField);
                        }
                    }
                }
                supportedReadAbleFieldsMap.put(getClass(), supportedFields);
            }
        }
        return supportedFields;
    }

    private List<Field> getWriteSupportedFields(Class roClass) {
        List<Field> supportedFields = supportedWriteAbleFieldsMap.get(roClass);
        if (supportedFields == null) {
            synchronized (AbstractRedisObj.class) {
                supportedFields = new ArrayList<>();
                Field[] declaredFields = getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (!Modifier.isFinal(declaredField.getModifiers()) && JudgeUtils
                        .allIsNull(declaredField.getAnnotation(RedisIgnore.class),
                            declaredField.getAnnotation(RedisWriteIgnore.class))) {
                        declaredField.setAccessible(true);
                        Type genericType = declaredField.getGenericType();
                        if(isSupportDataType(genericType)){
                            supportedFields.add(declaredField);
                        }
                    }
                }
                supportedWriteAbleFieldsMap.put(getClass(), supportedFields);
            }
        }
        return supportedFields;
    }

    private Boolean isSupportDataType(Type genericType) {
        if (genericType == Integer.class || genericType == int.class) {
            return true;
        } else if (genericType == Long.class || genericType == long.class) {
            return true;
        } else if (genericType == String.class) {
            return true;
        } else if (genericType == Boolean.class || genericType == boolean.class) {
            return true;
        } else if (genericType == Byte.class || genericType == byte.class) {
            return true;
        } else if (genericType == Short.class || genericType == short.class) {
            return true;
        } else if (genericType == BigDecimal.class) {
            return true;
        } else if (genericType == Date.class || genericType == Timestamp.class) {
            return true;
        } else if (((Class) genericType).isEnum()) {
            return true;
        }
        return false;
    }

    private static final ConcurrentHashMap<Class, List<Field>> supportedWriteAbleFieldsMap =
        new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class, List<Field>> supportedReadAbleFieldsMap =
        new ConcurrentHashMap<>();
}
