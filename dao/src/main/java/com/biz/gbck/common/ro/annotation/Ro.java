package com.biz.gbck.common.ro.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by defei on 8/22/16.
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface Ro {

    String hashKeyPrefix() default "";

    String idSortSetKey() default "";

}
