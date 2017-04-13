package com.biz.redis.annotation;

import java.lang.annotation.*;

/**
 * 属性注解  获取属性存取的key
 * 如果有此注解 则当前进行更新保存的对象，需增加当前对象id到当前key的value
 * @author jun.liu(by xiaoyu)
 * @date 2016年8月11日
 * @reviewer 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FieldSortedSet {
	
	String prefix() default "";
	
	String key();

	String score() default "";
}
