package com.biz.redis.annotation;

import java.lang.annotation.*;

/**
 * @reviewer 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RoLock {
	
	String key();
}
