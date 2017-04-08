package com.depotnextdoor.core.util;

import com.google.common.collect.Maps;
import java.lang.reflect.Field;
import java.util.Map;

public class MapUtil {
    /**
     * 将Object对象转换成Map<String, String>
     *
     * @throws Exception
     */
    public static Map<String, String> objectToMap(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            Map<String, String> map = Maps.newHashMap();
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj).toString());
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
