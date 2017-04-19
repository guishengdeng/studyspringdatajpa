package com.biz.redis.trans.impl;

import com.biz.redis.trans.*;
import com.biz.redis.trans.impl.convertor.BasicConverter;
import com.biz.redis.trans.impl.convertor.BeanConverter;
import com.biz.redis.trans.impl.convertor.EnumConverter;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author yanweijin
 * @date 2016/12/22
 */
public class TranslatorBuilder {

    private static Translator defaultTranslator;
    private DataTransformer dataTransformer;
    private List<Bucket> converterBuckets = Lists.newArrayList();
    private BeanRegistry beanRegistry;
    private ConverterRegistry converterRegistry;

    public static TranslatorBuilder createBuilder() {
        return new TranslatorBuilder().addDefaultConverter();
    }

    /**
     * 获取默认 translator 单例
     */
    public synchronized static Translator getDefaultTranslatorSingleton() {
        if (defaultTranslator == null) {
            defaultTranslator = TranslatorBuilder.createBuilder().build();
        }
        return defaultTranslator;
    }


    public TranslatorBuilder addValueConverter(Class clazz, ValueConverter valueConverter) {
        converterBuckets.add(new Bucket(clazz, valueConverter));
        return this;
    }

    public TranslatorBuilder addValueConverter(ConverterMatcher converterMatcher, ValueConverter valueConverter) {
        converterBuckets.add(new Bucket(converterMatcher, valueConverter));
        return this;
    }

    public <T extends Translator> Translator build() {
        DefaultTranslator dt = new DefaultTranslator();
        if (converterRegistry == null) {
            converterRegistry = new DefaultConverterRegistry();
        }

        if (beanRegistry == null) {
            DefaultBeanRegistry beanRegistry0 = new DefaultBeanRegistry();
            beanRegistry0.setConverterRegistry(converterRegistry);
            beanRegistry = beanRegistry0;
        }
        if (dataTransformer == null) {
            dataTransformer = new DefaultDataTransformer();
        }
        this.addValueConverter(RedisObject.class, new BeanConverter(this.converterRegistry, this.beanRegistry));


        for (Bucket converterBucket : converterBuckets) {
            if (converterBucket.clazz != null) {
                converterRegistry.registerConverter(converterBucket.clazz, converterBucket.valueConverter);
            } else {
                converterRegistry.registerConverter(converterBucket.converterMatcher, converterBucket.valueConverter);
            }
        }

        dt.setBeanRegistry(beanRegistry);
        dt.setConverterRegistry(converterRegistry);
        dt.setDataTransformer(dataTransformer);
        return dt;
    }

    private TranslatorBuilder addDefaultConverter() {
        this
                .addValueConverter(Boolean.class, BasicConverter.withBoolean)
                .addValueConverter(boolean.class, BasicConverter.withBoolean)
                .addValueConverter(Byte.class, BasicConverter.withByte)
                .addValueConverter(byte.class, BasicConverter.withByte)
                .addValueConverter(Short.class, BasicConverter.withShort)
                .addValueConverter(short.class, BasicConverter.withShort)
                .addValueConverter(Integer.class, BasicConverter.withInteger)
                .addValueConverter(int.class, BasicConverter.withInteger)
                .addValueConverter(Long.class, BasicConverter.withLong)
                .addValueConverter(long.class, BasicConverter.withLong)
                .addValueConverter(Character.class, BasicConverter.withCharacter)
                .addValueConverter(char.class, BasicConverter.withCharacter)
                .addValueConverter(Double.class, BasicConverter.withDouble)
                .addValueConverter(double.class, BasicConverter.withDouble)
                .addValueConverter(Float.class, BasicConverter.withFloat)
                .addValueConverter(float.class, BasicConverter.withFloat)
                .addValueConverter(BigDecimal.class, BasicConverter.withBigDecimal)
                .addValueConverter(String.class, BasicConverter.withString)
                .addValueConverter(Date.class, BasicConverter.withDate)
                .addValueConverter(java.sql.Date.class, BasicConverter.withSqlDate)
                .addValueConverter(Timestamp.class, BasicConverter.withTimestamp)
                .addValueConverter(Time.class, BasicConverter.withSqlTime)
                .addValueConverter(EnumConverter.MARCHER, new EnumConverter())
                .addValueConverter(BeanConverter.MATCHER, new BeanConverter(converterRegistry, beanRegistry))


        ;

        return this;
    }

    private static class Bucket {
        Class clazz;
        ValueConverter valueConverter;
        ConverterMatcher converterMatcher;

        public Bucket(Class clazz, ValueConverter valueConverter) {
            this.clazz = clazz;
            this.valueConverter = valueConverter;
        }

        public Bucket(ConverterMatcher converterMatcher, ValueConverter valueConverter) {
            this.valueConverter = valueConverter;
            this.converterMatcher = converterMatcher;
        }
    }
}
