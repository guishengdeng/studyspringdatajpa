package com.biz.message.support;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.exceptions.SystemException;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

/**
 * spring包扫描过滤器,用于发现被扫描类中潜在的消息队列定义枚举
 *
 * @author yanweijin
 * @usage 配置在@ComponentScan的filter的include filter中,或xml&lt;context:component-scan>的对应属性<br>
 * 需要配合{@link QueueAutomaticCreationProcessor}使用
 * @reviewer
 * @see QueueAutomaticCreationProcessor
 * @since 2016年7月31日
 */
public class QueueDefinitionScanFilter implements TypeFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    static final List<Class<Enum<?>>> container = new ArrayList<>();
    private Set<String> filteringInterfaces = Sets.newHashSet(
            "com.bozhi.message.QueueDefinition"
    );
    private String enumClassName = "java.lang.Enum";


    @SuppressWarnings("unchecked")
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        boolean concreate = metadataReader.getClassMetadata().isConcrete();
        if (!concreate) return false;

        String[] interfaces = metadataReader.getClassMetadata().getInterfaceNames();
        String superClassName = metadataReader.getClassMetadata().getSuperClassName();
        String className = metadataReader.getClassMetadata().getClassName();
        Set<String> interfaceSet = Sets.newHashSet(interfaces);
        interfaceSet.retainAll(filteringInterfaces);

        if (!interfaceSet.isEmpty()) {
            SystemAsserts.isTrue(Objects.equal(superClassName, enumClassName),
                    "消息组件接口的实现必须是一个枚举,当前类%s不匹配", className);
            try {
                Class<?> clz = ClassUtils.forName(className, null);
                container.add((Class<Enum<?>>) clz);
                logger.info("向container注册了新的枚举:" + clz.getName());
            } catch (ClassNotFoundException | LinkageError e) {
                throw new SystemException(e);
            }
        }
        return false;
    }


}
