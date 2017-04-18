package com.biz.gbck.enums.depot;

import com.biz.core.exceptions.SystemException;
import com.google.common.collect.Range;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

/**
 * 送货或者发货时间枚举
 *
 * @author zhangcheng
 * @date 2017/1/23
 * @reviewer
 * @see
 */
public enum PeriodsOfDeliveriesTime {

    now("19分钟送达", Range.openClosed(0, 1000)),
    half_hour("29分钟送达", Range.openClosed(1000, 2000)),
    one_hour("59分钟送达", Range.openClosed(2000, 5000)),
    today("二十四小时之内发货", Range.greaterThan(5000));

    private final String title;
    private final Range<Integer> range;

    PeriodsOfDeliveriesTime(String title, Range<Integer> supportRange) {
        this.title = title;
        this.range = supportRange;
    }

    public String getTitle() {
        return title;
    }

    public boolean supported(Integer distance) {
        return range.contains(distance);
    }

    public Date computeDeliveryTime(Date begin) {
        switch (this) {
            case half_hour:
                return DateUtils.addMinutes(begin, 29);
            case now:
                return DateUtils.addMinutes(begin, 19);
            case one_hour:
                return DateUtils.addMinutes(begin, 59);
            case today:
                Calendar cal = Calendar.getInstance();
                cal.setTime(begin);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                return cal.getTime();
        }
        throw new SystemException("");
    }

    public static PeriodsOfDeliveriesTime valueOfDistance(Integer distance) {
        for (PeriodsOfDeliveriesTime e : values()) {
            if (e.supported(distance)) {
                return e;
            }
        }
        return null;
    }
}
