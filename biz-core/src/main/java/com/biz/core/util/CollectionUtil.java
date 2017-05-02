package com.biz.core.util;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Java8 集合工具
 *
 * @author: liubin
 * @date 4/28/17 09:25
 */
public class CollectionUtil {

    public static <T> Stream<T> collectionAsStream(Collection<T> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.stream();
    }

}
