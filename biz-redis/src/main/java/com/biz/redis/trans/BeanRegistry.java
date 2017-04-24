package com.biz.redis.trans;

import com.biz.core.property.ExtPropertyDescriptor;
import java.util.List;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public interface BeanRegistry {

    /**
     * 根据ro对象获取其属性描述符扩展,首先从缓存中查找,如果没有找到,则注册一次并缓存,然后获取
     */
    List<ExtPropertyDescriptor> findProperties(Class beanClazz);

    /**
     * 创建一个 ro 新对象
     */
    <RO> RO newBeanInstance(Class<RO> beanClazz);

    /**
     * 注册 ro 类,使其属性描述符得到缓存
     */
    void registerObject(Class beanClazz);


    ConverterRegistry getConverterRegistry();

}
