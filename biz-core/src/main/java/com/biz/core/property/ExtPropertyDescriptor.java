package com.biz.core.property;

import com.google.common.collect.Maps;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 属性描述符扩展:由于PropertyDescriptor中保存属性类型的字段是一个weak引用,会导致gc时将该字段回收
 * 在下次获取属性类型时根据getter/setter来反射真实类型
 * 然而我们redis object使用了泛型属性,反射出的getter/setter是bridge方法,其参数/返回值是泛型超类
 * 因此不能在gc后获取到正确的类型,导致redis反射出错
 *
 * 封装PropertyDescriptor,将属性类型改为强引用
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年9月19日
 */
public class ExtPropertyDescriptor {

    private PropertyDescriptor propertyDescriptor;

    private final Class<?> propertyType;

    private final Method readMethod;

    private final Method writeMethod;

    private final String name;

    private final Field propertyField;

    private final Class<?> beanClass;

    private final Map<Class, Object> annotationMapping = Maps.newHashMap();

    public ExtPropertyDescriptor(PropertyDescriptor pd, Class<?> beanClass) {
        super();
        this.beanClass = beanClass;
        this.propertyDescriptor = pd;
        this.propertyType = pd.getPropertyType();
        this.readMethod = pd.getReadMethod();
        this.writeMethod = pd.getWriteMethod();
        this.name = pd.getName();
        this.propertyField = ReflectionUtils.findField(beanClass, this.name);
    }

    public Class getPropertyType() {
        return propertyType;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public String getName() {
        return name;
    }

    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    public void setPropertyDescriptor(PropertyDescriptor propertyDescriptor) {
        this.propertyDescriptor = propertyDescriptor;
    }


    /**
     * 在属性上找注解,只有第一次查询时进行反射,之后从缓存获取
     *
     * 会依次在: 属性名对应的字段上/属性的 getter 方法上/属性的 setter 方法上寻找指定类型的注解
     * 一旦找到就会写入缓存,并停止查询
     * 第二次寻找时,从缓存中获取
     *
     *
     * 也就是说,当同时在 field,getter,setter 标记同一个注解时,只有 field 上的注解有效,以此类推
     *
     * 如果都没有找到,也会缓存标记,下次不再查找;
     *
     * 没有找到注解则返回 null;
     */
    @SuppressWarnings("unchecked")
    public <A extends Annotation> A findAnnotation(Class<A> annotationClass) {
        Object cached = annotationMapping.get(annotationClass);

        if (cached == null) {
            A annotation = null;
            if (propertyField != null) {
                annotation = AnnotationUtils.findAnnotation(propertyField, annotationClass);
            }
            if (annotation == null && readMethod != null) {
                annotation = AnnotationUtils.findAnnotation(readMethod, annotationClass);
            }
            if (annotation == null && writeMethod != null) {
                annotation = AnnotationUtils.findAnnotation(writeMethod, annotationClass);
            }
            annotationMapping.put(annotationClass, annotation == null ? void.class : annotation);
            return annotation;
        } else if (cached == void.class) {
            return null;
        } else {
            return (A) cached;
        }
    }
}
