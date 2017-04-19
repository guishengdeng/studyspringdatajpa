package com.biz.redis.trans.impl.convertor;

import com.biz.redis.trans.ValueConverter;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yanweijin
 * @date 2016/12/23
 */
public final class BasicConverter {

    public static final Logger logger = LoggerFactory.getLogger(BasicConverter.class);
    public static final ValueConverter<Byte> withByte = new AbstractNumberConvertor<Byte>() {
        @Override
        protected Byte parseValue(String data) {
            return Byte.valueOf(data);
        }
    };
    public static final ValueConverter<Short> withShort = new AbstractNumberConvertor<Short>() {
        @Override
        protected Short parseValue(String data) {
            return Short.valueOf(data);
        }
    };
    public static final ValueConverter<Integer> withInteger = new AbstractNumberConvertor<Integer>() {
        @Override
        protected Integer parseValue(String data) {
            return Integer.valueOf(data);
        }
    };
    public static final ValueConverter<Long> withLong = new AbstractNumberConvertor<Long>() {
        @Override
        protected Long parseValue(String data) {
            return Long.valueOf(data);
        }
    };
    public static final ValueConverter<Double> withDouble = new AbstractNumberConvertor<Double>() {
        @Override
        protected Double parseValue(String data) {
            return Double.valueOf(data);
        }
    };
    public static final ValueConverter<Float> withFloat = new AbstractNumberConvertor<Float>() {
        @Override
        protected Float parseValue(String data) {
            return Float.valueOf(data);
        }
    };
    public static final ValueConverter<BigDecimal> withBigDecimal = new AbstractSimpleValueConverter<BigDecimal>() {
        @Override
        protected BigDecimal parseValue(String data) {
            return new BigDecimal(data).movePointLeft(8);
        }

        @Override
        protected String val2String(BigDecimal val) {
            return val.movePointRight(8).toString();
        }
    };
    public static final ValueConverter<Date> withDate = new AbstractSimpleValueConverter<Date>() {
        @Override
        protected Date parseValue(String data) {
            return new Date(Long.valueOf(data));
        }

        @Override
        protected String val2String(Date val) {
            return val.getTime() + "";
        }
    };
    public static final ValueConverter<java.sql.Date> withSqlDate = new AbstractSimpleValueConverter<java.sql.Date>() {
        @Override
        protected java.sql.Date parseValue(String data) {
            return new java.sql.Date(Long.valueOf(data));
        }

        @Override
        protected String val2String(java.sql.Date val) {
            return val.getTime() + "";
        }
    };
    public static final ValueConverter<Timestamp> withTimestamp = new AbstractSimpleValueConverter<Timestamp>() {
        @Override
        protected Timestamp parseValue(String data) {
            return new Timestamp(Long.valueOf(data));
        }

        @Override
        protected String val2String(Timestamp val) {
            return val.getTime() + "";
        }
    };
    public static final ValueConverter<Time> withSqlTime = new AbstractSimpleValueConverter<Time>() {
        @Override
        protected Time parseValue(String data) {
            return Time.valueOf(data);
        }

        @Override
        protected String val2String(Time val) {
            return val.toString();
        }
    };
    public static final ValueConverter<Boolean> withBoolean = new AbstractSimpleValueConverter<Boolean>() {
        @Override
        protected Boolean parseValue(String data) {
            return Boolean.valueOf(data);
        }

        @Override
        protected String val2String(Boolean val) {
            return val.toString();
        }
    };
    public static final ValueConverter<Character> withCharacter = new AbstractSimpleValueConverter<Character>() {
        @Override
        protected Character parseValue(String data) {
            if (data.length() != 1) {
                logger.error("char 类型的值出错");
            }
            return data.charAt(0);
        }

        @Override
        protected String val2String(Character val) {
            return val.toString();
        }
    };
    public static final ValueConverter<String> withString = new AbstractSimpleValueConverter<String>() {
        @Override
        protected String parseValue(String data) {
            return data;
        }

        @Override
        protected String val2String(String val) {
            return val;
        }
    };
    private BasicConverter() {
    }

}
