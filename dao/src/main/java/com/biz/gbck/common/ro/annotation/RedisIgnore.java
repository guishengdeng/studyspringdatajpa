package com.biz.gbck.common.ro.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by defei on 8/22/16.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface RedisIgnore {
    
}
