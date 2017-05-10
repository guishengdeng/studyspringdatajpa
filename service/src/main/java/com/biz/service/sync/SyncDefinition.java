package com.biz.service.sync;

import java.lang.annotation.*;

/**
 * 同步方法的定义信息,用于展示在后台
 * @author yanweijin
 * @since 2016年9月27日
 * @usage 
 * @reviewer
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SyncDefinition {

	String syncId();
	String desc() default "";
	
}
